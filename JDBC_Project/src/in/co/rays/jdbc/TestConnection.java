package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestConnection {
	public static void main(String[] args)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		System.out.println("Connection successfully: "+conn.getCatalog());
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("select * from marksheet");
	while (rs.next()) {
		System.out.println(rs.getInt("id"));
		System.out.println(rs.getInt("rollNo"));
		System.out.println(rs.getString("name"));
		System.out.println(rs.getInt("phy"));
		System.out.println(rs.getInt("chm"));
		System.out.println(rs.getInt("maths"));
		System.out.println(rs.getInt("total"));
		System.out.println(rs.getFloat("persentage"));
		System.out.println(rs.getString("Result"));
		System.out.println("-------------------------------");
	}
	}

}
