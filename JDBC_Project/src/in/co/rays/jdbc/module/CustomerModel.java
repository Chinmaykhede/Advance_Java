package in.co.rays.jdbc.module;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.rays.util.JDBCDataSource;

public class CustomerModel {
	public int nextPK() throws Exception {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(customerId) from customer");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PK = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return PK + 1;

	}

	public void add(CustomerBean b) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into customer values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, b.getCustomerName());
			pstmt.setString(3, b.getEmail());
			pstmt.setString(4, b.getPhoneNo());
			pstmt.setString(5, b.getAddress());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Inserted " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
}