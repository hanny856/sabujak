package reply.model;

import java.net.ConnectException;
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
import sabujak.domain.DTO;

class ReplyDAO {
	private DataSource ds;
	ReplyDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	public ArrayList<DTO> list(int currentPage, int pageSize,int c_no) {
		ArrayList<DTO> list = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ReplySQL.LIST_PAGE;
	    int startRow = (currentPage-1)*pageSize;
	    int endRow = currentPage*pageSize;
	    try {
	    	con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, c_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cre_no = rs.getInt("CRE_NO");
				String cre_cont = rs.getString("CRE_CONT");
				Date cre_date = rs.getDate("CRE_DATE");
				int c_no_fk = rs.getInt("C_NO_FK");
				String email_fk = rs.getString("EMAIL_FK");
				DTO dto = new DTO(cre_no,cre_cont,cre_date,c_no_fk,email_fk);
				list.add(dto);
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

	public void insert(DTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = ReplySQL.INSERT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getCre_cont());
			pstmt.setInt(2, dto.getC_no_fk());
			pstmt.setString(3, dto.getEmail_fk());
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

	public void del(int cre_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = ReplySQL.DEL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cre_no);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	public long getTotalCount() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = ReplySQL.COUNT;
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

}
