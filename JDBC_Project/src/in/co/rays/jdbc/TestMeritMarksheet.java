package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMeritMarksheet {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");

		System.out.println("Connection Successfully:" + conn.getCatalog());

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(
"select *,(phy+chm+maths)as total from marksheet where phy>=33 and chm>=33 and maths>=33 order by total desc limit 0,3");

		while (rs.next()) {

			System.out.println(rs.getInt("id"));
			System.out.println(rs.getInt("rollNo"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getInt("phy"));
			System.out.println(rs.getInt("chm"));
			System.out.println(rs.getInt("maths"));
			int total = rs.getInt("phy") + rs.getInt("chm") + rs.getInt("maths");
			double persentage = (total / 3);
			System.out.println(total);
			System.out.println(persentage);
			System.out.println("-------------------------------");

		}

	}
}
