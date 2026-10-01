package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class CustomerModel {
	public int nextPk() throws Exception {
		Connection conn = null;
		int Pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(customerId)from customer");
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

	public void add(CustomerBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into customer values(?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhoneNo());
			pstmt.setString(5, bean.getAddress());
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

	public void delete(int customerId) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from customer where customerId = ?");
			pstmt.setInt(1, customerId);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Deleted " + i + "row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(CustomerBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update customer set address=? where customerId=?");
//			pstmt.setString(1, bean.getCustomerName());
//			pstmt.setString(2, bean.getEmail());
//			pstmt.setString(3, bean.getPhoneNo());
			pstmt.setString(1, bean.getAddress());
			pstmt.setInt(2, bean.getCustomerId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record updated " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(CustomerBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from customer where 1=1");
		List list = new ArrayList();
		Connection conn = null;
		try {
			if (bean != null) {
				if (bean.getCustomerId() > 0) {
					sql.append(" customerId=" + bean.getCustomerId());
				}
				if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
					sql.append(" and customerName like'" + bean.getCustomerName() + "%'");
				}
				if (bean.getEmail() != null && bean.getEmail().length() > 0) {
					sql.append(" and email like'" + bean.getEmail() + "%'");
				}
				if (bean.getPhoneNo() != null) {
					sql.append(" and phoneNo'" + bean.getPhoneNo() + "'");
				}
				if (bean.getAddress() != null && bean.getAddress().length() > 0) {
					sql.append(" and address like'" + bean.getAddress() + "%'");
				}

			}
			if (pageSize > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + "," + pageSize);
			}
			System.out.println("SQL=================>" + sql.toString());
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setCustomerId(rs.getInt("customerId"));
				bean.setCustomerName(rs.getString("customerName"));
				bean.setEmail(rs.getString("email"));
				bean.setPhoneNo(rs.getString("phoneNo"));
				bean.setAddress(rs.getString("address"));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
