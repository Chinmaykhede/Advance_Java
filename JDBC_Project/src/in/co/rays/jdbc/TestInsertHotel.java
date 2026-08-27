package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertHotel {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");

		System.out.println("Connection Successfully:" + conn.getCatalog());

		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("insert into hotel values(1005,'Rambagh Palace','Jaipur',6.0,'6369215432')");
        System.out.println("Record inserted "+ i + "row affected");
}
}