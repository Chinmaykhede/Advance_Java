package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class StudentModel {
	public int nextPK() {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select max(studentId) from student");
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

	public void add(StudentBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into student values(?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getMobileNo());
			pstmt.setString(5, bean.getCourse());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data Inserted" + i + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.transcationRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void update(StudentBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update student set name=? where studentId=?");
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getStudentId());
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

	public void delete(StudentBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from student where studentId=?");
			pstmt.setInt(1, bean.getStudentId());
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
//	public List search(StudentBean bean,int pageNo,int pageSize) {
//		StringBuffer sb = new StringBuffer("select * from student where 1=1");
//		List list = new ArrayList();
//		Connection conn = null;
//		try {
//			if(bean!=null) {
//				if(bean.getStudentId()!=0 && bean.getStudentId()>0) {
//				  sb.append("studentId"+bean.getStudentId());
//				}
//				if(bean.getName()!=null && bean.getName().length()>0) {
//					sb.append(" name like'"+bean.getName()+"%'")
//				}
//				
//			}
//		}
//	}
}
