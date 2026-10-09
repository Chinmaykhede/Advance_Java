package in.co.rays.jdbc.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.raya.jdbc.preparedstatement.UserBean;
import in.com.rays.util.JDBCDataSource;

public class BankingModel {
	public int nextPK() throws Exception {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from banking");
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

	public void add(BankingBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into banking values(?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setLong(2, bean.getAccountNo());
			pstmt.setString(3, bean.getHolderName());
			pstmt.setString(4, bean.getAccountType());
			pstmt.setDouble(5, bean.getBalance());
			pstmt.setString(6, bean.getBranch());
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

	public void update(BankingBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update banking set id=?,accountNo=?,holderName=?,accountType=?,balance=?,balance=?");
			pstmt.setInt(1, bean.getId());
			pstmt.setLong(2, bean.getAccountNo());
			pstmt.setString(3, bean.getHolderName());
			pstmt.setString(4, bean.getAccountType());
			pstmt.setDouble(5, bean.getBalance());
			pstmt.setString(6, bean.getBranch());
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

	public void delete(BankingBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from banking where id=?");
			pstmt.setInt(1, bean.getId());
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

//	public List search(BankingBean bean, int pageNo, int pageSize) {
//		StringBuffer sql = new StringBuffer("select * from user where 1=1"); 
//		List list = new ArrayList();
//		Connection conn = null;
//		try {
//			if (bean != null) {
//				if (bean.getId() > 0) {
//					sql.append("and Id =" + bean.getId());
//				}
//				if (bean.getAccountNo() != null && bean.getAccountNo().length() > 0) {
//					sql.append(" and AccountNo like '" + bean.getAccountNo() + "%'");
//				}
//				if (bean.getHolderName() != null && bean.getHolderName().length() > 0) {
//					sql.append(" and holderName like '" + bean.getHolderName() + "%'");
//				}
//				if (bean.getAccountType() != null && bean.getAccountType().length() > 0) {
//					sql.append(" and accountType  = '" + bean.getAccountType() + "'");
//				}
//				if (bean.getBalance() != null && bean.getBalance().length()> 0) {
//					sql.append(" and balance = '" + bean.getBalance() + "'");
//					
//				if(bean.getBranch() != null && bean.getBranch().length()>0) {
//					sql.append(" and branch like '" + bean.getBranch() + "%'");
//				}
//				
//			}
//			if (pageSize > 0) {
//				int index = (pageNo - 1) * pageSize;
//				sql.append(" limit " + index + ", " + pageSize);
//			}
//
//			System.out.println("sql ====> " + sql.toString());
//			conn = JDBCDataSource.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				bean = new BankingBean();
//				bean.setId(rs.getInt("id"));
//				bean.setAccountNo(rs.getLong("accountNo"));
//				bean.setHolderName(rs.getString("holderName"));
//				bean.setAccountType(rs.getString("accountType"));
//				bean.setBalance(rs.getDouble("balance"));
//				bean.setBranch(rs.getString("branch"));
//				list.add(bean);
//			}
//			}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCDataSource.closeConnection(conn);
//		}

//		return list;
	}



