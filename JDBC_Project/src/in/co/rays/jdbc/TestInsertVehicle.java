package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertVehicle {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		
		System.out.println("Connection Successfully " + conn.getCatalog());
		
		Statement stmt = conn.createStatement();
		
		int i = stmt.executeUpdate("insert into vehicle values(56789034235,'Mahendra','Scropio S11','Black',17.40)");
		
		System.out.println("Row Inserted " + i + "row affected");
	}

}
