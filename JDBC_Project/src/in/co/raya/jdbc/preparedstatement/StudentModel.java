package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentModel {
	public void add(int id, String name, int age, int mark, String email) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into student values(?,?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setInt(4, mark);
			pstmt.setString(5, email);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("recored inserted successfully: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(int id) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from student where id=?");
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Recored Deleted Successfully: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void update(int id, String email) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update student set email=? where id = ?");
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Recored Updated: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

}
