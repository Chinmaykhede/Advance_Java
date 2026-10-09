package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class HotelModel {

	public void add(HotelBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into hotel values(?,?,?,?,?)");
			pstmt.setInt(1, bean.getRoomNo());
			pstmt.setString(2, bean.getRoomType());
			pstmt.setInt(3, bean.getFloor());
			pstmt.setDouble(4, bean.getPricePerNight());
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

	public void update(HotelBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update hotel set roomType=?, floor=?,pricePerNight=?,availability=? where roomNo=?");
			pstmt.setString(1, bean.getRoomType());
			pstmt.setInt(2, bean.getFloor());
			pstmt.setDouble(3, bean.getPricePerNight());
			pstmt.setBoolean(4, bean.isAvailability());
			pstmt.setInt(5, bean.getRoomNo());
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
	public void delete(HotelBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from hotel where roomNo=?");
			pstmt.setInt(1, bean.getRoomNo());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Delete " + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
}
//	public List search(HotelBean bean,int pageNo,int pageSize) {
//		StringBuffer sql = new StringBuffer("select * from hotel where 1=1");
//		List list = new ArrayList();
//		Connection conn = null;
//		try {
//			if(bean != null) {
//				if(bean.getRoomNo()>0) {
//					sql.append("and roomNo =" + bean.getRoomNo());
//				}
//				if (bean.getRoomType() != null && bean.getRoomType().length() > 0) {
//					sql.append(" and roomType like '" + bean.getRoomType() + "%'");
//			}
//				if (bean.getFloor() > 0) {
//				    sql.append(" and floor = " + bean.getFloor());
//				}
//				if (bean.getPricePerNight() > 0) {
//				    sql.append(" and pricePerNight like '" + bean.getPricePerNight() + "%'");
//				}
//                
//				if (bean.getAvailability() != null) {
//				    sql.append(" and availability = " + bean.getAvailability());
//				}
//
//	}
//}
