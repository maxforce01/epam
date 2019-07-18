package ua.nure.gunko.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gunko.rent.db.entity.Fine;

/**
 * @author maxforce01
 *
 */
public class FineDao {

	private static final String SQL_FIND_BY_ID = "select * from fines where user_id = ?";
	private static final String SQL_ALL_FINES = "select * from fines;";
	private static final String SQL_INSERT_FINE = "insert into fines (user_id,fine) values(?,?)";
	private static final String SQL_UPDATE_FINE = "update fines set fine = ? where user_id = ?";
	private static final String SQL_DELETE_FINE = "delete from fines where user_id = ?";
	
	
	/**
	 * @author maxforce01
	 * select fine by id
	 */
	public Fine findFineById(long id) {
		Fine fine = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			FineMapper mapper = new FineMapper();
			pstmt = con.prepareStatement(SQL_FIND_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				fine = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return fine;
	}
	
	/**
	 * @author maxforce01
	 * create new fine
	 */
	public  boolean createFine(Fine fine) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_FINE);
			pstmt.setLong(1, fine.getUser_id());
			pstmt.setInt(2, fine.getFine());
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
	public boolean updateFine(Fine fine) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FINE);
			pstmt.setLong(2, fine.getUser_id());
			pstmt.setInt(1, fine.getFine());
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
	 * @author maxforce01
	 * delete fine by id
	 */
	public boolean deleteFine(Fine fine) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_FINE);
			pstmt.setLong(1, fine.getUser_id());
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
	 * @author maxforce01
	 * select all fines
	 */
	public List<Fine> allFines() {
		List<Fine> list = new ArrayList<Fine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			FineMapper mapper = new FineMapper();
			pstmt = con.prepareStatement(SQL_ALL_FINES);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(mapper.mapRow(rs));
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
	
	private static class FineMapper implements EntityMapper<Fine>{

		@Override
		public Fine mapRow(ResultSet rs) {
			try {
				Fine fine = new Fine();
				fine.setId(rs.getInt(Fields.ENTITY__ID));
				fine.setUser_id(rs.getInt(Fields.FINE_USER_ID));
				fine.setFine(rs.getInt(Fields.FINE_PAYMENT));
				return fine;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
		
	}

}
