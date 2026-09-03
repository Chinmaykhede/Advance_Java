package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientModel {
	public void create() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"create table patient(patientId int primary key,patientName varchar(45),Disease varchar(45),doctorName varchar(45),admissionDate Date)");
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("table created " + i + "row affected");

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();

		} finally {
			conn.close();
		}
	}

	public int nextPk() throws Exception {
		Connection conn = null;
		int Pk = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysq://localhost:3306/testing", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(patientId)from patient");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return Pk + 1;
	}

	public void add(BeanPatient bean) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into patient values(?,?,?,?,?)");

			pstmt.setInt(1, bean.getPatientId());
			pstmt.setString(2, bean.getPatientName());
			pstmt.setString(3, bean.getDisease());
			pstmt.setString(4, bean.getDoctorName());
			pstmt.setDate(5, new java.sql.Date(bean.getAdmissionDate().getTime()));
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Inserted " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void delete(BeanPatient bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from patient where patientId=?");
			pstmt.setInt(1, bean.getPatientId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Deleted " + i + "row affected");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void update(BeanPatient bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update patient set patientName=? where patientId=?");
			pstmt.setString(1, bean.getPatientName());
			pstmt.setInt(2, bean.getPatientId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Updated " + i + "row affected");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

}
