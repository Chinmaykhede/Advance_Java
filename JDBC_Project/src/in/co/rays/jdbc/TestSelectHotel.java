package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelectHotel {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection successfully: " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from hotel");
		while(rs.next()) {
			System.out.println(rs.getLong("hotelId"));
			System.out.println(rs.getString("hotelName"));
			System.out.println(rs.getString("location"));
			System.out.println(rs.getDouble("rating"));
			System.out.println(rs.getString("contactNo"));
			System.out.println("------------------------------");
		}
		
	}

}
