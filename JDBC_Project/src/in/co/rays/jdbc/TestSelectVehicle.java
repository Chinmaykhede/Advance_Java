package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelectVehicle {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		System.out.println("Connection Successfully " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from vehicle");
		while(rs.next()) {
			System.out.println(rs.getLong("vehicleId"));
			System.out.println(rs.getString("vehicleName"));
			System.out.println(rs.getString("model"));
			System.out.println(rs.getString("color"));
			System.out.println(rs.getDouble("price"));
			System.out.println("--------------------------------");
		}
	}

}
