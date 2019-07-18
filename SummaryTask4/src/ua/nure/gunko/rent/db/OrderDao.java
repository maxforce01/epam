package ua.nure.gunko.rent.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gunko.rent.db.entity.Order;

/**
 * @author maxforce01
 *
 */
public class OrderDao {

	private static final String SQL_CREATE_ORDER = "insert into orders (user_id,car_id,session,number,date_start,date_end,payment,status) values (?,?,?,?,?,?,?,?);";
	private static final String SQL_FIND_ORDERS_BY_STATUS = "select * from orders where status = ? ";
	private static final String SQL_FIND_BY_ID = "select * from orders where id = ?";
	private static final String SQL_UPDATE_ORDER_STATUS = "update orders set status = ? where id = ?";
	private static final String SQL_UPDATE_ORDER_STATUS_AND_CAUSE = "update orders set status = ?,cause= ? where id = ?";
	private static final String SQL_ORDER_REPORT = "select * from orders where date_end between ? and ? and status = 'RETURNED';";
	/**
	 * @author maxforce01
	 * create new order
	 */
	public boolean createOrder(Order order) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
			try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_CREATE_ORDER);
			pstmt.setLong(1, order.getUser_id());
			pstmt.setLong(2, order.getCar_id());
			pstmt.setString(3, order.getSession());
			pstmt.setString(4, order.getNumber());
			pstmt.setDate(5, order.getDateStart());
			pstmt.setDate(6, order.getDateEnd());
			pstmt.setInt(7, order.getPayment());
			pstmt.setString(8, order.getStatus().toUpperCase());
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
	 * find orders between dates
	 */
	public List<Order> findOrdersByDates(Date one,Date two) {
		List<Order> list = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			OrderMapper mapper = new OrderMapper();
			pstmt = con.prepareStatement(SQL_ORDER_REPORT);
			pstmt.setDate(1, one);
			pstmt.setDate(2, two);
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
	
	/**
	 * @author maxforce01
	 * find orders with negative status
	 */
	public List<Order> findOrdersByStatus(String status) {
		List<Order> list = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			OrderMapper mapper = new OrderMapper();
			pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_STATUS);
			pstmt.setString(1, status);
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

	/**
	 * @author maxforce01
	 * find orders by id
	 */
	public Order findOrdersById(long id) {
		Order order = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			OrderMapper mapper = new OrderMapper();
			pstmt = con.prepareStatement(SQL_FIND_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				order = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return order;
	}
	
	/**
	 * @author maxforce01
	 * update order status
	 */
	public static boolean updateOrderStatus(Order order) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS);
			pstmt.setString(1, order.getStatus());
			pstmt.setLong(2, order.getId());
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
	 * update status and cause
	 */
	public static boolean updateOrderStatusAndCause(Order order) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean flag = false;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS_AND_CAUSE);
			pstmt.setString(1, order.getStatus());
			pstmt.setString(2, order.getCause());
			pstmt.setLong(3, order.getId());
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

	private static class OrderMapper implements EntityMapper<Order> {

		@Override
		public Order mapRow(ResultSet rs) {
			try {
				Order order = new Order();
				order.setId(rs.getLong(Fields.ENTITY__ID));
				order.setUser_id(rs.getLong(Fields.ORDER_USER_ID));
				order.setCar_id(rs.getLong(Fields.ORDER_CAR_ID));
				order.setSession(rs.getString(Fields.ORDER_USER_SESSION));
				order.setNumber(rs.getString(Fields.ORDER_USER_NUMBER));
				order.setDateStart(rs.getDate(Fields.ORDER_DATE_START));
				order.setDateEnd(rs.getDate(Fields.ORDER_DATE_END));
				order.setPayment(rs.getInt(Fields.ORDER_PAYMENT));
				order.setStatus(rs.getString(Fields.ORDER_STATUS));
				order.setCause(rs.getString(Fields.ORDER_CAUSE));
				return order;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}

	}

}
