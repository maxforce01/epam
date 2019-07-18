package ua.gunko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=243342");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from users");
		while(rs.next()) {
			System.out.println(rs.getInt("id")+" "+rs.getString("name"));
		}
		st.close();
		conn.close();
	}
}
