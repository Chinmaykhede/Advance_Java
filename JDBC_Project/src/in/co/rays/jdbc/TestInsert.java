package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestInsert {
	public static void main(String[] args)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		System.out.println("Connection successfully: "+conn.getCatalog());
	Statement stmt = conn.createStatement();
	int i = stmt.executeUpdate("insert into marksheet values(12,112,'Ranu',45,56,60,161,53.66,'pass')");
	System.out.println("record inserted "+ i + "row affected");
}
}
