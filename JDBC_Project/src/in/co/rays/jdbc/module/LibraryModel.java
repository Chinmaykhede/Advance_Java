package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class LibraryModel {
	public int nextPK() throws Exception {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(bookId) from library");
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

	public void add(LibraryBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Library values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getAuthor());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setBoolean(5, bean.isAvailability());
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

	public void update(LibraryBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update library set title=?,author=?,price=?, availability=? where bookId=?");
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getAuthor());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setBoolean(4, bean.isAvailability());
			pstmt.setInt(5, bean.getBookId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Updated " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(LibraryBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from library where bookId=?");
			pstmt.setInt(1, bean.getBookId());
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

	public List<LibraryBean> search(LibraryBean bean, int pageNo, int pageSize) throws SQLException {
		List<LibraryBean> list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from library where 1=1");
		Connection conn = null;

		if (bean != null) {
			if (bean.getBookId() > 0) {
				sql.append(" and bookId =" + bean.getBookId());
			}
			if (bean.getTitle() != null && bean.getTitle().length() > 0) {
				sql.append(" and title like '" + bean.getTitle() + "%'");
			}
			if (bean.getAuthor() != null && bean.getAuthor().length() > 0) {
				sql.append(" and author like '" + bean.getAuthor() + "%'");

			}
			if (bean.getPrice() > 0) {
				sql.append(" and price =" + bean.getPrice());

			}
			if (bean.isAvailability()) {
				sql.append(" and availability =" + bean.isAvailability());

			}

		}
		if (pageSize > 0) {
			int index = (pageNo - 1) * pageSize;
			sql.append(" limit " + index + "," + pageSize);
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LibraryBean();
				bean.setBookId(rs.getInt("bookId"));
				bean.setTitle(rs.getString("title"));
				bean.setAuthor(rs.getString("author"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailability(rs.getBoolean("availability"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return list;

	}

}
