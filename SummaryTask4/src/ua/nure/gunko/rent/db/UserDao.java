package ua.nure.gunko.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Hash;

/**
 * Data access object for User entity.
 * @author maxforce01
 */

public class UserDao {

	private static final String SQL__FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	private static final String SQL__FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

	private static final String SQL__CREATE_USER = "insert into users (login,email,password,role_id,locale,status) values(?,?,?,?,?,?)";

	private static final String SQL__UPDATE_USER = "update users set login = ? , email = ?, locale = ? where id = ?";
	
	private static final String SQL__DELETE_USER = "delete from users where id = ?";
	
	private static final String SQL__ALL_USERS = "select * from users where role_id != 1;";
	
	private static final String SQL__UPDATE_USER_TO_MANAGER = "update users set role_id = 2 where id = ?;";
	
	private static final String SQL_BAN_USER = "update users set status = 1 where id = ?;";
	
	private static final String SQL_UNBAN_USER = "update users set status = 0 where id = ?;";
	
	/**
	 * Returns a user with the given identifier.
	 *
	 * @param id User identifier.
	 * @return User entity.
	 */
	public User findUser(Long id) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			UserMapper mapper = new UserMapper();
			pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return user;
	}

	/**
	 * Returns a user with the given login.
	 *
	 * @param login User login.
	 * @return User entity.
	 * @throws SQLException
	 */
	public User findUserByLogin(String login) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			UserMapper mapper = new UserMapper();
			pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return user;
	}

	/**
	 * 
	 *
	 * @param User.
	 * @return boolean.
	 * @throws SQLException
	 */
	public static boolean createUser(User user) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL__CREATE_USER);
			pstmt.setString(1, user.getLogin());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, Hash.hash(user.getPassword()));
			pstmt.setInt(4, user.getRoleId());
			pstmt.setString(5, user.getLocale());
			pstmt.setBoolean(6, user.isStatus());
			pstmt.executeUpdate();
			pstmt.close();
			flag = true;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
			flag = false;
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return flag;
	}
	/**
	 * 
	 *
	 * @param User.
	 * @return boolean.
	 * @throws SQLException
	 */
	public static boolean updateUser(User user) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL__UPDATE_USER);
			pstmt.setString(1, user.getLogin());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getLocale());
			pstmt.setLong(4, user.getId());
			pstmt.executeUpdate();
			pstmt.close();
			flag = true;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
			flag = false;
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return flag;
	}
	
	/**
	 * Ban user
	 *
	 * @param User.
	 * @return boolean.
	 * @throws SQLException
	 */
	public static boolean banUser(User user) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_BAN_USER);
			pstmt.setLong(1, user.getId());
			pstmt.executeUpdate();
			pstmt.close();
			flag = true;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
			flag = false;
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return flag;
	}
	
	/**
	 * Unban user
	 *
	 * @param User.
	 * @return boolean.
	 * @throws SQLException
	 */
	public static boolean unBanUser(User user) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_UNBAN_USER);
			pstmt.setLong(1, user.getId());
			pstmt.executeUpdate();
			pstmt.close();
			flag = true;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
			flag = false;
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return flag;
	}
	
	
	/**
	 * Change user's role to manager
	 *
	 * @param User.
	 * @return boolean.
	 * @throws SQLException
	 */
	public static boolean updateUserToManager(User user) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL__UPDATE_USER_TO_MANAGER);
			pstmt.setLong(1, user.getId());
			pstmt.executeUpdate();
			pstmt.close();
			flag = true;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
			flag = false;
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return flag;
	}
	
	
	public List<User> findAllUsers() {
		List<User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			UserMapper carMapper = new UserMapper();
			pstmt = con.prepareStatement(SQL__ALL_USERS);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(carMapper.mapRow(rs));
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return list;
	}
	
	/**
	 * @author maxforce01 delete user by id
	 */
	public static boolean deleteUser(User user) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL__DELETE_USER);
			pstmt.setLong(1, user.getId());
			pstmt.executeUpdate();
			pstmt.close();
			flag = true;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
			flag = false;
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return flag;
	}
	


	/**
	 * Extracts a user from the result set row.
	 */
	private static class UserMapper implements EntityMapper<User> {

		@Override
		public User mapRow(ResultSet rs) {
			try {
				User user = new User();
				user.setId(rs.getLong(Fields.ENTITY__ID));
				user.setLogin(rs.getString(Fields.USER__LOGIN));
				user.setPassword(rs.getString(Fields.USER__PASSWORD));
				user.setEmail(rs.getString(Fields.USER__EMAIL));
				user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
				user.setLocale(rs.getString(Fields.USER__LOCALE));
				user.setStatus(rs.getBoolean(Fields.USER__STATUS));
				return user;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
