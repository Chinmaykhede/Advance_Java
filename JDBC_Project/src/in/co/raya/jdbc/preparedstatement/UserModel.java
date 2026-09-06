package in.co.raya.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.rays.util.JDBCDataSource;

public class UserModel {

	public void createTable() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"create table user ( userId int  primary key ,userfirstName varchar(45),userlastName varchar(45),userloginId varchar(45),userpassword varchar(45),dob Date)");
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Table Created " + i + "Row affected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public int nextPk() throws Exception {
		Connection conn = null;
		int Pk = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(userId)from user");
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

	public void add(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLoginId());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Inserted " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from user where userId=?");
			pstmt.setInt(1, bean.getId());
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

	public void update(UserBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update user set userfirstName=? where userId=?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setInt(2, bean.getId());
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

	// findByPk() == select * from st_user where id = ?
	// findByLogin() == select * from st_user where loginId = ?
	// authenticate() == select * from st_user where loginId = ? and password = ?

	public UserBean findByPk(int userId) throws Exception {

		Connection conn = null;
		UserBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from user where userId = ?");

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("userId"));
				bean.setFirstName(rs.getString("userfirstName"));
				bean.setLastName(rs.getString("userlastName"));
				bean.setLoginId(rs.getString("userloginId"));
				bean.setPassword(rs.getString("userpassword"));
				bean.setDob(rs.getDate("dob"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public UserBean findByLogin(String userloginId) throws Exception {

		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from user where userloginId = ?");

			pstmt.setString(1, userloginId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("userId"));
				bean.setFirstName(rs.getString("userfirstName"));
				bean.setLastName(rs.getString("userlastName"));
				bean.setLoginId(rs.getString("userloginId"));
				bean.setPassword(rs.getString("userpassword"));
				bean.setDob(rs.getDate("dob"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public UserBean authenticate(String userloginId, String userpassword) throws Exception {

		UserBean bean = new UserBean();

		bean = findByLogin(userloginId);

		if (bean != null && bean.getPassword().equals(userpassword)) {
			return bean;
		}

		return null;

	}

}
