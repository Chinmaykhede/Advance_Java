package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestDelete {
	public static void main(String[] args)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		System.out.println("Connection successfully: "+conn.getCatalog());
	Statement stmt = conn.createStatement();
	int i = stmt.executeUpdate("delete from marksheet where id = 11");
	System.out.println("record delete "+ i + "row affected");
	}
}
