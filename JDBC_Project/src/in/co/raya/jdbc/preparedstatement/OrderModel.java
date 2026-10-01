package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.com.rays.util.JDBCDataSource;

public class OrderModel {
	public int nextPK() {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select max(orderId) from st_order");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PK = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK + 1;
	}

	public void add(OrderBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into  st_order values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setDate(2, new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setDouble(3, bean.getAmount());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getCustomerId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(OrderBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_order where orderId=?");
			pstmt.setInt(1, bean.getOrderId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record deleted successfully " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
}
