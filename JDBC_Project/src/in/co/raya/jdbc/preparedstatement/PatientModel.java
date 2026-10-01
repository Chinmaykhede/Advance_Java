package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.rays.util.JDBCDataSource;

public class PatientModel {
	public void create() throws Exception {
		Connection conn = null;
		try {
			JDBCDataSource.getConnection();
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
			JDBCDataSource.closeConnection(conn);
		}
	}

	public int nextPk() throws Exception {
		Connection conn = null;
		int Pk = 0;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(patientId)from patient");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return Pk + 1;
	}

	public void add(BeanPatient bean) throws SQLException {
		Connection conn = null;
		try {
			conn=JDBCDataSource.getConnection();
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
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(BeanPatient bean) throws Exception {
		Connection conn = null;
		try {
			conn=JDBCDataSource.getConnection();
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
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(BeanPatient bean) throws Exception {
		Connection conn = null;
		try {
			conn=JDBCDataSource.getConnection();
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
			JDBCDataSource.closeConnection(conn);
		}
	}

}
