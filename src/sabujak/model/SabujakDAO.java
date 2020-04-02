package sabujak.model;

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
import sabujak.domain.Sabujak;

class SabujakDAO {
	private DataSource ds;
	int addview;
	SabujakDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	public ArrayList<Sabujak> list(int currentPage, int pageSize) {
		ArrayList<Sabujak> list = new ArrayList<Sabujak>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = SabujakSQL.LIST_PAGE;
	    int startRow = (currentPage-1)*pageSize;
	    int endRow = currentPage*pageSize;
	    try {
	    	con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int c_no = rs.getInt("C_NO");
				String email_fk_pk = rs.getString("EMAIL_FK_PK");
				String c_sub = rs.getString("C_SUB");
				Date c_date= rs.getDate("C_DATE");
				int c_view = rs.getInt("C_VIEW");
				Sabujak sab = new Sabujak(c_no,email_fk_pk,c_sub,c_date,c_view);
				list.add(sab);
			}
			return list;
	    }catch(SQLException se) {
	    	return null;
	    }finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	long getTotalCount() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = SabujakSQL.COUNT;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				long count = rs.getLong(1);
				return count;
			}else {
				return -1L;
			}
		}catch(SQLException se) {
			return -1L;
		}finally {
			try {
				if(rs != null ) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	Sabujak getBoard(int c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = SabujakSQL.CONTENT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String c_sub = rs.getString("C_SUB");
				String email_fk_pk = rs.getString("EMAIL_FK_PK");
				Date c_date = rs.getDate("C_DATE");
				int c_view = rs.getInt("C_VIEW");		
				String c_cont = rs.getString("C_CONT");
				String c_ofname = rs.getString("C_OFNAME");
				String c_fname = rs.getString("C_FNAME");
				Sabujak sab = new Sabujak(c_no,c_sub, email_fk_pk, c_date, c_view, c_cont,c_ofname,c_fname);
				return sab;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void getView(int c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = SabujakSQL.GETVIEW;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int c_view = rs.getInt("C_VIEW");
				addview=c_view+1;
			}
			setView(c_no,addview);
		}catch(SQLException se) {
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}

	private void setView(int c_no, int addview2) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = SabujakSQL.SETVIEW;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, addview);
			pstmt.setInt(2, c_no);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
		
	}

	void insert(Sabujak sabujak) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = SabujakSQL.INSERT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sabujak.getEmail_fk_pk());
			pstmt.setString(2, sabujak.getC_sub());
			pstmt.setString(3, sabujak.getC_cont());
			pstmt.setString(4, sabujak.getC_fname());
			pstmt.setString(5, sabujak.getC_ofname());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
		
	}

	void del(int c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = SabujakSQL.DEL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}

	void up(Sabujak sabujak) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = SabujakSQL.UP;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sabujak.getC_sub());
			pstmt.setString(2, sabujak.getC_cont());
			pstmt.setInt(3, sabujak.getC_no());
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}

	void update(Sabujak sabujak) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = SabujakSQL.UPDATE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sabujak.getC_sub());
			pstmt.setString(2, sabujak.getC_cont());
			pstmt.setString(3, sabujak.getC_fname());
			pstmt.setString(4, sabujak.getC_ofname());
			pstmt.setInt(5, sabujak.getC_no());
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}




}
