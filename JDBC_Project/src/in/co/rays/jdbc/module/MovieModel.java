package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class MovieModel {

	public int nextPk() {
		Connection conn = null;
		int Pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(movieId) from movie");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Pk = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return Pk + 1;
	}

	public void add(MovieBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into movie values(?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getGenre());
			pstmt.setInt(4, bean.getDuration());
			pstmt.setDouble(5, bean.getRating());
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

	public void update(MovieBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update movie set title=?,genre=?,duration=?,rating=? where movieId=?");
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getGenre());
			pstmt.setInt(3, bean.getDuration());
			pstmt.setDouble(4, bean.getRating());
			pstmt.setInt(5, bean.getMovieId());
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

	public void delete(MovieBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from movie where movieId=?");
			pstmt.setInt(1, bean.getMovieId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Deleted" + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List<MovieBean> search(MovieBean bean, int pageNo, int pageSize) {

		List<MovieBean> list = new ArrayList<MovieBean>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from movie where 1=1");
		if (bean != null) {
			if (bean.getMovieId() > 0) {
				sql.append(" and movieId =" + bean.getMovieId());
			}
			if (bean.getTitle() != null && bean.getTitle().length() > 0) {
				sql.append(" and title like '" + bean.getTitle() + "%'");
			}
			if (bean.getGenre() != null && bean.getGenre().length() > 0) {
				sql.append(" and genre like '" + bean.getGenre() + "%'");
			}

			if (bean.getDuration() > 0) {
				sql.append(" and duration =" + bean.getDuration());
			}
			if (bean.getRating() > 0) {
				sql.append(" and rating =" + bean.getRating());
			}
		}
		if (pageNo > 0) {
			int index = (pageNo - 1) * pageSize;
			sql.append(" limit " + index + "," + pageSize);
		}
		System.out.println("sql===========>" + sql.toString());

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MovieBean();
				bean.setMovieId(rs.getInt("movieId"));
				bean.setTitle(rs.getString("title"));
				bean.setGenre(rs.getString("genre"));
				bean.setDuration(rs.getInt("duration"));
				bean.setRating(rs.getDouble("rating"));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public MovieBean findByPk(int movieId) {
		Connection conn = null;
		MovieBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from movie where movieId=?");
			pstmt.setInt(1, movieId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MovieBean();
				bean.setMovieId(rs.getInt("movieId"));
				bean.setTitle(rs.getString("title"));
				bean.setGenre(rs.getString("genre"));
				bean.setDuration(rs.getInt("duration"));
				bean.setRating(rs.getDouble("rating"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
return bean;
	}

}
