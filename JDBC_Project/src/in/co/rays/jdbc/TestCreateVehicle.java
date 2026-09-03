package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCreateVehicle {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		System.out.println("Connection Successfully "+ conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("create table Vehicle(vehicleId Long, vehicleName varchar(45),model varchar(45),color varchar(45),price double)");
		System.out.println("Record Create " + i + "row affected");
	}

}
