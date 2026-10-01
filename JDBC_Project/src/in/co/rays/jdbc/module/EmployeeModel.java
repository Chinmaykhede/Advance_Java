package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.rays.util.JDBCDataSource;

public class EmployeeModel {
	public void create() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"create table employee(employeeId int primary key,name varchar(45),designation varchar(45),salary varchar(45),joiningDate Date)");
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("table created " + i + "row affected");

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public int nextPK() {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select max(employeeId) from employee");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PK = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK + 1;
	}

	public void add(EmployeeBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into employee values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDesignation());
			pstmt.setInt(4, bean.getSalary());
			pstmt.setDate(5, new java.sql.Date(bean.getJoiningDate().getTime()));
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record inserted " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(EmployeeBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update employee set name=? where employeeId=? ");
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getEmployeeId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Updated" + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	public void delete(EmployeeBean bean) {
		Connection conn=null;
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from employee where employeeId=?");
			pstmt.setInt(1, bean.getEmployeeId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record updated"+i+" row affected");	
		}catch(Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
}
