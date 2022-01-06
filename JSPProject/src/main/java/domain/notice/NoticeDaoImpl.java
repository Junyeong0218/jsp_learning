package domain.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnectionMgr;

public class NoticeDaoImpl implements NoticeDao {
	
	DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	@Override
	public Notice getNoticeDtlById(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from notice where id = ?";
		Notice notice = new Notice();

		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			notice.setId(id);
			notice.setTitle(rs.getString("title"));
			notice.setWriterId(rs.getString("writer_id"));
			notice.setRegDate(rs.getTimestamp("regdate").toLocalDateTime());
			notice.setHit(rs.getInt("hit"));
			notice.setFiles(rs.getString("files") == null? "": rs.getString("files"));
			notice.setContent(rs.getString("content"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return notice;
	}
	
	@Override
	public int getNextIdByCurrentId(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM ( "
					+ "SELECT id, ROW_NUMBER() over() AS rownum FROM notice "
					+ "WHERE regdate > (SELECT regdate FROM notice WHERE id = ?)) AS t "
					+ "WHERE t.rownum = 1";
		int nextId = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				nextId = rs.getInt("id");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return nextId;
	}
	
	@Override
	public int getPrevIdByCurrentId(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM ( "
					+ "SELECT id, ROW_NUMBER() over(order by id desc) AS rownum FROM notice "
					+ "WHERE regdate < (SELECT regdate FROM notice WHERE id = ?)) AS t "
					+ "WHERE t.rownum = 1";
		int prevId = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				prevId = rs.getInt("id");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return prevId;
	}
	
	@Override
	public Notice getIdAndTitleById(int id) {
		if(id == 0) {
			return null;
		} else {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select id, title from notice where id = ?";
			Notice notice = null;

			try {
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					notice = new Notice();
					notice.setId(rs.getInt("id"));
					notice.setTitle(rs.getString("title"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			
			return notice;
		}
	}
	
	@Override
	public List<NoticeView> getNoticesByListId(String option, String keyword, int list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rownum = (list - 1) * 10 + 1;
		String sql = "SELECT * FROM ( "
					+ "SELECT *, ROW_NUMBER() over(order by id desc) AS rownum FROM notice_view) AS t "
					+ "WHERE t.rownum between "+ rownum +" and " + (rownum + 9);
		
		if(keyword != null) {
			sql = "SELECT * FROM ( "
					+ "SELECT *, ROW_NUMBER() over(ORDER BY id DESC) AS rownum "
					+ "FROM notice_view "
					+ "WHERE " + option + " LIKE ? "
					+ ") AS t "
				+ "WHERE t.rownum BETWEEN " + rownum + " and " + (rownum + 9);
		}
		
		List<NoticeView> notices = new ArrayList<NoticeView>();

		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			if(keyword != null) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeView notice = new NoticeView();
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriterId(rs.getString("writer_id"));
				notice.setRegDate(rs.getTimestamp("regdate").toLocalDateTime());
				notice.setHit(rs.getInt("hit"));
				notice.setFiles(rs.getString("files") == null? "": rs.getString("files"));
				notice.setCommentCnt(rs.getInt("cmt_count"));
				
				notices.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return notices;
	}
	
	@Override
	public List<NoticeView> getNewestNotices() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, title, regdate FROM ( "
						+ "SELECT *, ROW_NUMBER() over(order by id desc) AS rownum FROM notice_view) AS t "
						+ "WHERE t.rownum between 1 and 5";
		List<NoticeView> notices = new ArrayList<NoticeView>();

		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeView notice = new NoticeView();
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setRegDate(rs.getTimestamp("regdate").toLocalDateTime());
				
				notices.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return notices;
	}
	
	@Override
	public int getNoticeCount(String option, String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from notice";
		int result = 0;
		
		if(keyword != null) {
			sql = "select count(*) from notice where " + option + " like ?";
		}

		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			
			if(keyword != null) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			rs.next();
			result = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}

}
