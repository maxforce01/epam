package ua.nure.gunko.rent.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {


	//////////////
	// singleton///
	//////////////
	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null)
			instance = new DBManager();
		return instance;
	}

		public Connection getConnection() {
		Connection con = null;
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/Rental");
			con = ds.getConnection();
		} catch (NamingException | SQLException ex) {
			con = getConnectionWithDriverManager();
		}
		return con;
	}

	private DBManager() {
	}

	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con Connection to be committed and closed.
	 */
	public void commitAndClose(Connection con) {
		try {
			con.commit();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con Connection to be rollbacked and closed.
	 */
	public void rollbackAndClose(Connection con) {
		try {
			con.rollback();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnectionWithDriverManager() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/rental?" + "user=root&password=243342");
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
