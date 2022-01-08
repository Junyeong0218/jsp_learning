package domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnectionMgr;

public class UserDaoImpl implements UserDao {
	
	private DBConnectionMgr pool;
	
	public UserDaoImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public User selectUserByUsername(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			con = pool.getConnection();
			sql = "select * from user_mst where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			user = new User();
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setSignupDate(rs.getTimestamp("signup_date").toLocalDateTime());
				user.setUpdateDate(rs.getTimestamp("update_date").toLocalDateTime());
				user.setPermission(rs.getInt("permission"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return user;
	}
	
	@Override
		public int signinByUser(User user) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			int result = 0;
			
			try {
				con = pool.getConnection();
				sql = "select count(um.username) + count(um2.password) "
					+ "from user_mst um "
					+ "left outer join user_mst um2 on(um.id = um2.id and um2.password = ?) "
					+ "where um.username = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getUsername());
				rs = pstmt.executeQuery();
				
				rs.next();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			
			return 0;
		}

}
