package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDeleteVehicle {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		System.out.println("Connection Successfully "+ conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("delete from vehicle where vehicleId = 567890233");
		System.out.println("Record deleted "+ i + "row affected");
		
	}

}
