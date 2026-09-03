package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorModel {
	public void create() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"create table vendor(vendorId int primary key,vendorName varchar(45),mobileNo varchar(45),address varchar(45),serviceType varchar(45))");
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("table created " + i + "row affected");

		} catch (Exception e) {
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
	public void add(BeanVendor bean) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into vendor values(?,?,?,?,?)");

			pstmt.setInt(1, bean.getVendorId());
			pstmt.setString(2, bean.getVendorName());
			pstmt.setString(3, bean.getMobileNo());
			pstmt.setString(4, bean.getAddress());
			pstmt.setString(5, bean.getServiceType());
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
	public void delete(BeanVendor bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from vendor where vendorId=?");
			pstmt.setInt(1, bean.getVendorId());
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
	public void update(BeanVendor bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update vendor set vendorName=? where vendorId=?");
			pstmt.setString(1,bean.getVendorName());
			pstmt.setInt(2, bean.getVendorId());
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
