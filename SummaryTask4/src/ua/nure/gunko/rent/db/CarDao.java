package ua.nure.gunko.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gunko.rent.db.entity.Car;

/**
 * @author maxforce01
 *
 */
public class CarDao {

	private static final String SQL__FIND_ALL_CARS = "SELECT * FROM cars where status = 0;";

	private static final String SQL_FIND_CAR_BY_ID = "Select * from cars where id = ?;";

	private static final String SQL_FIND_CAR_BY_VIN = "Select * from cars where vin = ?;";

	private static final String SQL_UPDATE_CAR_STATUS = "update cars set status = ? where id = ?";

	private static final String SQL_ALL_CARS = "select * from cars;";

	private static final String SQL_CREATE_CAR = "insert into cars (brand,model,class_id,vin,number,price,status) values (?,?,?,?,?,?,?)";

	private static final String SQL_UPDATE_CAR = "update cars set brand = ?,model = ?,class_id = ?,vin = ?,number = ?,price = ?,status = ? where id = ?;";

	private static final String SQL_DELETE_CAR = "delete from cars where id = ?;";

	private static final String SQL_FILTER_CARS = "select * from cars where status = 0 and ";

	private static final String SQL_SORT_CARS = "select * from cars where status = 0 ";

	/**
	 * @author maxforce01 find car by id
	 */
	public Car findCarById(int id) {
		Car car = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarMapper carMapper = new CarMapper();
			pstmt = con.prepareStatement(SQL_FIND_CAR_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				car = carMapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return car;
	}
	
	/**
	 * @author maxforce01 find car by vin
	 */
	public Car findCarByVin(String vin) {
		Car car = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarMapper carMapper = new CarMapper();
			pstmt = con.prepareStatement(SQL_FIND_CAR_BY_VIN);
			pstmt.setString(1, vin);
			rs = pstmt.executeQuery();
			if (rs.next())
				car = carMapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return car;
	}

	/**
	 * @author maxforce01 find all free cars for clients
	 */
	public List<Car> findAllFreeCars() {
		List<Car> list = new ArrayList<Car>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarMapper carMapper = new CarMapper();
			pstmt = con.prepareStatement(SQL__FIND_ALL_CARS);
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
	 * @author maxforce01 change car status on rent or returned
	 */
	public static boolean updateCarStatus(Car car) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_CAR_STATUS);
			pstmt.setBoolean(1, car.isStatus());
			pstmt.setLong(2, car.getId());
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
	 * @author maxforce01 insert new car
	 */
	public static boolean insertCar(Car car) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		ResultSet rs = null;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_CREATE_CAR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, car.getBrand());
			pstmt.setString(2, car.getModel());
			pstmt.setInt(3, car.getCarClass().getId());
			pstmt.setString(4, car.getVIN());
			pstmt.setString(5, car.getNumber());
			pstmt.setInt(6, car.getPrice());
			pstmt.setBoolean(7, car.isStatus());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				car.setId(rs.getInt("GENERATED_KEY"));
			}
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
	 * @author maxforce01 update car by id
	 */
	public static boolean updateCar(Car car) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_CAR);
			pstmt.setString(1, car.getBrand());
			pstmt.setString(2, car.getModel());
			pstmt.setInt(3, car.getCarClass().getId());
			pstmt.setString(4, car.getVIN());
			pstmt.setString(5, car.getNumber());
			pstmt.setInt(6, car.getPrice());
			pstmt.setBoolean(7, car.isStatus());
			pstmt.setLong(8, car.getId());
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
	 * @author maxforce01 delete car by id
	 */
	public static boolean deleteCar(Car car) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_CAR);
			System.out.println(car.getId());
			pstmt.setLong(1, car.getId());
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
	 * @author maxforce01 select all cars
	 */
	public List<Car> findAllCars() {
		List<Car> list = new ArrayList<Car>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarMapper carMapper = new CarMapper();
			pstmt = con.prepareStatement(SQL_ALL_CARS);
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
	 * @author maxforce01 select all cars with parameters
	 */
	public List<Car> filterCars(String query) {
		List<Car> list = new ArrayList<Car>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarMapper carMapper = new CarMapper();
			pstmt = con.prepareStatement(SQL_FILTER_CARS + query);
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
	 * @author maxforce01 sort cars by parameter
	 */
	public List<Car> sortCars(String query) {
		List<Car> list = new ArrayList<Car>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			CarMapper carMapper = new CarMapper();
			pstmt = con.prepareStatement(SQL_SORT_CARS + query);
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

	private static class CarMapper implements EntityMapper<Car> {

		@Override
		public Car mapRow(ResultSet rs) {

			try {
				Car car = new Car();
				car.setId(rs.getLong(Fields.ENTITY__ID));
				car.setBrand(rs.getString(Fields.CAR_BRAND));
				car.setModel(rs.getString(Fields.CAR_MODEL));
				car.setVIN(rs.getString(Fields.CAR_VIN));
				car.setNumber(rs.getString(Fields.CAR_NUMBER));
				car.setPrice(rs.getInt(Fields.CAR_PRICE));
				car.setCarClass(new CarClassDao().findById(rs.getLong(Fields.CAR_CLASS_ID)));
				car.setStatus(rs.getBoolean(Fields.CAR_STATUS));
				return car;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}

		}

	}

}
