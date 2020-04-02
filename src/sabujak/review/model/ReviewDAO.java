package sabujak.review.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sabujak.domain.Review;
class ReviewDAO {
	private DataSource ds;
	ReviewDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("ne:"+ne);
		}
	}
	ArrayList<Review> list(int currentPage, int pageSize, int h_no){
		ArrayList<Review> list = new ArrayList<Review>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ReviewSQL.LIST;
		int startRow = (currentPage-1)*pageSize;
		int endRow = currentPage * pageSize;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_no);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int rev_no = rs.getInt("rev_no");
				int rev_good = rs.getInt("rev_good");
				String rev_sub = rs.getString("rev_sub");
				String rev_cont = rs.getString("rev_cont");
				Date rev_date = rs.getDate("rev_date");
				String email_fk = rs.getString("email_fk");
				Review rev = 
						new Review(rev_no, h_no, rev_good, rev_sub ,rev_cont, rev_date, email_fk);
				list.add(rev);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(SQLException se){}
		}
		
	}
	long getTotalCount(int h_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ReviewSQL.COUNT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long count = rs.getLong(1);
				return count;
			}else {
				return -1L;
			}
		}catch(SQLException se) {
			System.out.println("totalcountSe: "+se);
			return -1L;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	Review contentRev(int rev_no, int h_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =  ReviewSQL.CONTENT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_no);
			pstmt.setInt(2,h_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int rev_good = rs.getInt("rev_good");
				String rev_sub = rs.getString("rev_sub");
				String rev_cont = rs.getString("rev_cont");
				Date rev_date = rs.getDate("rev_date");
				String email_fk = rs.getString("email_fk");
				Review rev = 
						new Review(rev_no, h_no, rev_good, rev_sub ,rev_cont, rev_date, email_fk);
				return rev;
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
	
	ArrayList<Review> calcultotal(int h_no){
		ArrayList<Review> list = new ArrayList<Review>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =  ReviewSQL.REVTOTAL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,h_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int rev_no = rs.getInt("rev_no");
				int rev_good = rs.getInt("rev_good");
				String rev_sub = rs.getString("rev_sub");
				String rev_cont = rs.getString("rev_cont");
				Date rev_date = rs.getDate("rev_date");
				String email_fk = rs.getString("email_fk");
				Review rev = 
					new Review(rev_no, h_no, rev_good, rev_sub ,rev_cont, rev_date, email_fk);
				list.add(rev);
			}
			return list;
		}catch(SQLException se){
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
	void insertRev(Review review) {
		String sql = ReviewSQL.INSERT;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,review.getH_no());
			pstmt.setInt(2,review.getRev_good());
			pstmt.setString(3, review.getRev_cont());
			pstmt.setString(4, review.getEmail_fk());
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("review: "+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	void delRev(int rev_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = ReviewSQL.DEL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_no);
			pstmt.executeUpdate();
		}catch(SQLException se) {
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se){}
		}
	}
	void updateRev(Review dto) {
		String sql = ReviewSQL.UPDATE;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,dto.getRev_good());
			pstmt.setString(2, dto.getRev_cont());
			pstmt.setInt(3, dto.getRev_no());
			pstmt.setInt(4, dto.getH_no());
			
			int i = pstmt.executeUpdate();
		}catch(SQLException se){
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
}