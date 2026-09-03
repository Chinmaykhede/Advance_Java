package in.co.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomModel {

	public static void main(String[] args) throws Exception {

		//Serch();
		add();
		//delete();
	}
	
	
	

	private static void add() throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		Statement stmt = conn.createStatement();

		int i = stmt.executeUpdate("insert into hotel values(45 , 'rajvir' , 'indore' , 4 , '908764677')");
		
		System.out.println(i+"Row affected");
		
		
	
		
		// TODO Auto-generated method stub
		
	}

	private static void Serch() throws ClassNotFoundException, SQLException  {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from hotel");
		
		while(rs.next()) {
			System.out.println(rs.getLong("hotelId"));
			System.out.println(rs.getString("hotelName"));
			
		}
		
		
	}

}
