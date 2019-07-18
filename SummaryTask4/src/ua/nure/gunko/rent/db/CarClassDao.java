package ua.nure.gunko.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gunko.rent.db.entity.CarClass;

/**
 * @author maxforce01
 *
 */
public class CarClassDao {

	private static final String SQL_FIND_CLASS_BY_ID = "select * from classes where id = ?";
	
	private static final String SQL_ALL_CLASSES ="select * from classes;";

	/**
	 * @author maxforce01
	 * find car class by id
	 */
	public CarClass findById(long id) {
		CarClass carClass = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarClassMapper mapper = new CarClassMapper();
			pstmt = con.prepareStatement(SQL_FIND_CLASS_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				carClass = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return carClass;
	}
	
	/**
	 * @author maxforce01
	 * find all classes
	 */
	public List<CarClass> findAllClasses() {
		List<CarClass> list = new ArrayList<CarClass>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarClassMapper carMapper = new CarClassMapper();
			pstmt = con.prepareStatement(SQL_ALL_CLASSES);
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
	

	private static class CarClassMapper implements EntityMapper<CarClass> {

		@Override
		public CarClass mapRow(ResultSet rs) {

			try {
				CarClass carClass = new CarClass();
				carClass.setId(rs.getInt(Fields.ENTITY__ID));
				carClass.setCarClass(rs.getString(Fields.CLASS));
				return carClass;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}

		}

	}
}
