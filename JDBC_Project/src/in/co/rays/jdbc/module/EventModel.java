package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.rays.util.JDBCDataSource;

public class EventModel {

	public int nextPK() throws Exception {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(eventId) from event");
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

	public void add(EventBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into event values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getEventName());
			pstmt.setDate(3, bean.getEventDate());
			pstmt.setString(4, bean.getVenue());
			pstmt.setString(5, bean.getVenue());
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

	public void update(EventBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update event set eventName=?,eventDate=?,venue=?, organizer=? where eventId=?");
			pstmt.setString(1, bean.getEventName());
			pstmt.setDate(2, bean.getEventDate());
			pstmt.setString(3, bean.getVenue());
			pstmt.setString(4, bean.getOrganizer());
			pstmt.setInt(5, bean.getEventId());
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

	public void delete(EventBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from event where eventId=?");
			pstmt.setInt(1, bean.getEventId());
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

}
