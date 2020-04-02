package notice.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import sabujak.domain.Notice;
import java.sql.*;

class NoticeDAO {
	private DataSource ds;
	NoticeDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	ArrayList<Notice> list(int currentPage, int pageSize) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = NoticeSQL.LIST_PAGE;
		
		int startRow = (currentPage-1)*pageSize;
		int endRow = currentPage*pageSize;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			System.out.println("07070");
			while(rs.next()) {
				int n_no = rs.getInt("N_NO");
				String n_sub = rs.getString("N_SUB");
				Date n_date = rs.getDate("N_DATE");
				String email_fk = rs.getString("EMAIL_FK");
				System.out.println("3"+n_no+"/"+n_sub+"/"+n_date+"/"+email_fk);
				Notice n = new Notice(n_no, n_sub, n_date, email_fk);
				list.add(n);
			}
			return list;
		}catch(SQLException se) {
			return null;
		}finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException sqe) {	
		}
	}
	}
	long getTotalCount() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = NoticeSQL.COUNT;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				long count =rs.getLong(1);
				return  count;
			}else {
				return -1L;
			}
		}catch (SQLException sqqe) {
			return -1L;
		}finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException sqqqe) {	
		}
	}
	}
	void insert(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = NoticeSQL.INSERT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getN_sub());
			pstmt.setString(2, notice.getN_cont());
			pstmt.setString(3, notice.getEmail_fk());
			pstmt.setString(4, notice.getFname());
			pstmt.setString(5, notice.getOfname());
			pstmt.executeUpdate();	
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException ssqqqe) {}
		}
	}
	Notice getNotice(int n_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = NoticeSQL.CONTENT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, n_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String n_sub = rs.getString("n_sub");
				String n_cont = rs.getString("n_cont");
				Date n_date = rs.getDate("n_date");
				String email_fk = rs.getString("email_fk");
				String fname = rs.getString("fname");
				String ofname = rs.getString("ofname");
				Notice n = new Notice(n_no, n_sub, n_cont, n_date, email_fk,fname,ofname);
				return n;
			}else {
				return null;
			}
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
		void update(Notice notice) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = NoticeSQL.UPDATE;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, notice.getEmail_fk());
				pstmt.setString(2, notice.getN_sub());
				pstmt.setString(3, notice.getN_cont());
				pstmt.setString(4, notice.getFname());
				pstmt.setString(5, notice.getOfname());
				pstmt.setInt(6, notice.getN_no());
				pstmt.executeUpdate();
			}catch(SQLException se) {
				System.out.println("update se: "+se);
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se) {}
			}
		}
		void del(int n_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = NoticeSQL.DEL;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, n_no);
				pstmt.executeUpdate();
			}catch(SQLException se) {
				System.out.println("del se: "+se);
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se) {}
			}
		}
	
	}

