package sabujak.hobby.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sabujak.domain.Hboard;

class HboardDAO {
	private DataSource ds;
	HboardDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
		}
	}
	ArrayList<Hboard> list(int currentPage, int pageSize){
		ArrayList<Hboard> list = new ArrayList<Hboard>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = HboardSQL.LIST;
		int startRow = (currentPage-1)*pageSize;
		int endRow = currentPage*pageSize;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int h_no = rs.getInt("h_no");
				int hcode_fk = rs.getInt("hcode_fk");
				String h_sub = rs.getString("h_sub");
				String h_cont = rs.getString("h_cont");
				int h_like = rs.getInt("h_like");
				Date h_date = rs.getDate("h_date");
				int h_view = rs.getInt("h_view");
				String email_fk = rs.getString("email_fk");
				String fname = rs.getString("fname");
				String ofname = rs.getString("ofname");
				Hboard hb = 
						new Hboard(h_no, hcode_fk, h_sub, h_cont, h_like, h_date, h_view, email_fk,fname,ofname);
				list.add(hb);
			}
			return list;
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(SQLException se){}
		}
	}
	ArrayList<Hboard> listView(int currentPage, int pageSize){
		ArrayList<Hboard> list = new ArrayList<Hboard>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = HboardSQL.LISTVIEW;
		int startRow = (currentPage-1)*pageSize;
		int endRow = currentPage*pageSize;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int h_no = rs.getInt("h_no");
				int hcode_fk = rs.getInt("hcode_fk");
				String h_sub = rs.getString("h_sub");
				String h_cont = rs.getString("h_cont");
				int h_like = rs.getInt("h_like");
				Date h_date = rs.getDate("h_date");
				int h_view = rs.getInt("h_view");
				String email_fk = rs.getString("email_fk");
				String fname = rs.getString("fname");
				String ofname = rs.getString("ofname");
				Hboard hb = 
						new Hboard(h_no, hcode_fk, h_sub, h_cont, h_like, h_date, h_view, email_fk, fname ,ofname );
				list.add(hb);
			}
			return list;
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(SQLException se){}
		}
	}
	long getTotalCount(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = HboardSQL.COUNT;
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
				if(rs != null) rs.close();
				if(stmt != null)stmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void insertB(Hboard hboard) {
		String sql = HboardSQL.INSERT;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hboard.getHcode_fk());
			pstmt.setString(2, hboard.getH_sub());
			pstmt.setString(3,hboard.getH_cont());
			pstmt.setString(4, hboard.getEmail_fk());
			pstmt.setString(5, hboard.getFname());
			pstmt.setString(6, hboard.getOfname());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	Hboard content(int h_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = HboardSQL.CONTENT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int hcode_fk = rs.getInt("hcode_fk");
				String h_sub = rs.getString("h_sub");
				String h_cont = rs.getString("h_cont");
				int h_like = rs.getInt("h_like");
				Date h_date = rs.getDate("h_date");
				int h_view = rs.getInt("h_view");
				String email_fk = rs.getString("email_fk");
				String fname = rs.getString("fname");
				String ofname = rs.getString("ofname");
				Hboard hb = 
						new Hboard(h_no, hcode_fk, h_sub, h_cont, h_like, h_date, h_view, email_fk,fname,ofname);
				return hb;
			}else {
				return null;
			}
		}catch(SQLException se){
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void del(int h_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = HboardSQL.DEL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_no);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se){}
		}
	}
	void update(Hboard dto) {
		String sql = HboardSQL.UPDATE;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getHcode_fk());
			pstmt.setString(2,dto.getH_sub());
			pstmt.setString(3, dto.getH_cont());
			pstmt.setString(4,dto.getFname());
			pstmt.setString(5, dto.getOfname());
			pstmt.setInt(6, dto.getH_no());
			
			int i = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("update : "+se);
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void viewUp(int h_no, int h_view){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = HboardSQL.VIEWUP;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (h_view+1));
			pstmt.setInt(2, h_no);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se){}
		}
	}
}
