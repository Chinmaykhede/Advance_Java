package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.raya.jdbc.preparedstatement.CustomerBean;
import in.com.rays.util.JDBCDataSource;

public class HospitalModel {
	public int nextPk() {
		Connection conn = null;
		int Pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select max(patientId) from hospital");
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

	public void add(HospitalBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into hospital values(?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getAge());
			pstmt.setString(4, bean.getBloodGroup());
			pstmt.setString(5, bean.getDisease());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Inserted" + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(HospitalBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update hospital set name=? where patientId=?");
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getPatientId());
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

	public void delete(HospitalBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from hospital where patientId=?");
			pstmt.setInt(1, bean.getPatientId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record deleted" + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(HospitalBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from customer where 1=1");
		List list = new ArrayList();
		Connection conn = null;
		try {
			if (bean != null) {
				if (bean.getPatientId() > 0) {
					sql.append(" patientId=" + bean.getPatientId());
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like'" + bean.getName() + "%'");
				}
				if(bean.getAge()>0 ) {
					sql.append(" and age like'" + bean.getAge() + "%'");
				}

				if (bean.getBloodGroup() != null && bean.getBloodGroup().length() > 0) {
					sql.append(" and bloodGroup like'" + bean.getBloodGroup() + "%'");
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
				bean = new HospitalBean();
				bean.setPatientId(rs.getInt("patientId"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
				bean.setBloodGroup(rs.getString("bloodGroup"));
				bean.setDisease(rs.getString("disease"));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
