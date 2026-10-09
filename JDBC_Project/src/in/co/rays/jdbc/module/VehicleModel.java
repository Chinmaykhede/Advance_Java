package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class VehicleModel {
	public int nextPK() throws Exception {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(vehicleId) from vehicle");
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

	public void add(VehicleBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into vehicle values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getBrand());
			pstmt.setString(3, bean.getModel());
			pstmt.setString(4, bean.getColor());
			pstmt.setInt(5, bean.getYear());
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

	public void update(VehicleBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update vehicle set brand=?,model=?,color=?,year=? where vehicleId=?");
			pstmt.setString(1, bean.getBrand());
			pstmt.setString(2, bean.getModel());
			pstmt.setString(3, bean.getColor());
			pstmt.setInt(4, bean.getYear());
			pstmt.setInt(5, bean.getVehicleId());
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

	public void delete(VehicleBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from vehicle where vehicleId=?");
			pstmt.setInt(1, bean.getVehicleId());
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

	public List<VehicleBean> search(VehicleBean bean, int pageNo, int pageSize) throws SQLException {
		List<VehicleBean> list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from vehicle where 1=1");
		Connection conn = null;

		if (bean != null) {
			if (bean.getVehicleId() > 0) {
				sql.append(" and vehicleId =" + bean.getVehicleId());
			}

			if (bean.getBrand() != null && bean.getBrand().length() > 0) {
				sql.append(" and brand like '" + bean.getBrand() + "%'");
			}
			if (bean.getModel() != null && bean.getModel().length() > 0) {
				sql.append(" and model like '" + bean.getModel() + "%'");
			}
			if (bean.getColor() != null && bean.getColor().length() > 0) {
				sql.append(" and color like '" + bean.getColor() + "%'");
			}
			if (bean.getYear() > 0) {
				sql.append(" and year =" + bean.getYear());

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
					bean = new VehicleBean();
					bean.setVehicleId(rs.getInt("vehicleId"));
					bean.setBrand(rs.getString("brand"));
					bean.setModel(rs.getString("model"));
					bean.setColor(rs.getString("color"));
					bean.setYear(rs.getInt("year"));
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}

		}
		return list;

	}

}
