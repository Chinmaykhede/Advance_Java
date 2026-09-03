package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Doctor {
	public static void main(String[] args) throws Exception, SQLException {
		// create();
		// search();
		insert();
		// update();
		// delete();
	}

	private static void create() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection Successfully...... " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate(
				"create table doctor(doctorId int primary key,doctorName varchar(45),specialization varchar(45),experince int,contactNo varchar(45))");
		System.out.println("Table Created " + i + " Row affected");
	}

	private static void search() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection Successfully...... " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from doctor");
		while (rs.next()) {
			System.out.println("DoctorId : " + rs.getLong("doctorId"));
			System.out.println("DoctorName : " + rs.getString("doctorName"));
			System.out.println("Specialization : " + rs.getString("specialization"));
			System.out.println("Experince : " + rs.getInt("experince"));
			System.out.println("ContactNo : " + rs.getString("contactNo"));
			System.out.println("-------------------------------------------");
		}
	}

	private static void insert() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection Successfully...... " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt
				.executeUpdate("insert into doctor values(1006,'Dr.Harshit Shrivastava','Surgeon',2,'7895543369')");
		System.out.println("Record Inserted " + i + "Row affected");
	}

	private static void update() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection Successfully...... " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("update doctor set doctorName= 'Dr.Harshit Soni' where doctorId=1005");
		System.out.println("Record updated " + i + "Row affected");
	}

	private static void delete() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		System.out.println("Connection Successfully...... " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("delete from doctor where doctorId=1005");
		System.out.println("Record delete " + i + "row affected");
	}
}
