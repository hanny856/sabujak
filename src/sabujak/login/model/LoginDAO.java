package sabujak.login.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sabujak.domain.Member;

class LoginDAO {
	private DataSource ds;
	LoginDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
		}
	}
	Member getMember(String email) {
		String sql = LoginSQL.CONTENT;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int m_no = rs.getInt("m_no");
				String m_pw = rs.getString("m_pw");
				String m_name = rs.getString("m_name");
				String m_call = rs.getString("m_call");
				String m_addr = rs.getString("m_addr");
				Date m_date = rs.getDate("m_date");
				int m_kind = rs.getInt("m_kind");
				return new Member(email, m_no, m_pw, m_name, m_call, m_addr, m_date, m_kind);
			}else {
				return null;
			}
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se){}
		}
	}
}
