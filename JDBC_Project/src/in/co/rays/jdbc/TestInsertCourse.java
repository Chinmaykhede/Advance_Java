package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertCourse {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection Successfully " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("insert into course values(5678902,'Advance Java','2 Month',80000,'Ansual Prajapat')");
		System.out.println("Data Inserted " + i + "row affected");

	}
}
