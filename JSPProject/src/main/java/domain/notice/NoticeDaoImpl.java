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
	public List<Notice> getNoticesByListId(int list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from notice order by id desc";
		List<Notice> notices = new ArrayList<Notice>();

		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice(rs.getInt("id"),
										   rs.getString("title"),
										   rs.getString("writer_id"),
										   rs.getString("content"),
										   rs.getTimestamp("regdate").toLocalDateTime(),
										   rs.getInt("hit"),
										   rs.getString("files") == null? "": rs.getString("files")
											);
				notices.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return notices;
	}

}
