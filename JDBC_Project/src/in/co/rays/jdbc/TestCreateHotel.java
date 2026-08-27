package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCreateHotel {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");

		System.out.println("Connection Successfully:" + conn.getCatalog());

		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate(
				"create table Hotel(hotelId long,hotelName varchar(45),location varchar(45),rating double,contactNo varchar(45))");
System.out.println("Record Create "+ i +"row affected");
	}
}
