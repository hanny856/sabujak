package join.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import join.domain.Join;


class JoinDAO {
	private DataSource ds;
	int addview;
	JoinDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	boolean checkADD(String email, String m_pw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select email,m_pw from member where email=?,m_pw=? ";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, m_pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String chekID=rs.getString("EMAIL");
				String chekPW=rs.getString("M_PW");
				if(chekID.equals(email)&&chekPW.equals(m_pw)) {
					return true;
				}else {
					return false;
				}			
			}else {
				return false;
			}
		}catch(SQLException se) {
			return false;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}

	boolean memberADD(Join join) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into MEMBER(EMAIL,M_NO,M_PW,M_NAME,M_CALL,M_ADDR) values(?,M_NO_SEQ.nextval,?,?,?,?)";
		String email=join.getEmail_fk_pk();
		String pw = join.getM_pw();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, join.getEmail_fk_pk());
			pstmt.setString(2, join.getM_pw());
			pstmt.setString(3,join.getM_name());
			pstmt.setString(4, join.getM_call());
			pstmt.setString(5, join.getM_addr());
			pstmt.executeUpdate();
			System.out.println(join.getM_name()+"1");
			boolean b1 = checkADD(email,pw);
			if(b1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se) {
			return false;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
}
