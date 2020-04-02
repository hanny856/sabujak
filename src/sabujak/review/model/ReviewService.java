package sabujak.review.model;

import java.util.ArrayList;

import sabujak.domain.Review;
import sabujak.review.vo.ListResultRev;

public class ReviewService {
	private ReviewDAO dao;
	private static final ReviewService instance = new ReviewService();
	private ReviewService() {
		dao = new ReviewDAO();
	}
	public static ReviewService getInstance() {
		return instance;
	}
	/*
	public ArrayList<Review> listRev(int h_no){
		System.out.println("3");
		return dao.listRev(h_no);
		
	}*/
	public void insertRevS(Review dto){
		dao.insertRev(dto);
		
	}
	public void delRevS(int rev_no){
		dao.delRev(rev_no);
		
	}
	public ListResultRev getListResultRev(int currentPage, int pageSize, int h_no) {
		ArrayList<Review> list = dao.list(currentPage, pageSize , h_no);
		long totalCount = dao.getTotalCount(h_no);
		ListResultRev r = new ListResultRev(currentPage, totalCount, pageSize, list);
		return r;
	}
	public Review contentRevS(int rev_no, int h_no){
		return dao.contentRev(rev_no, h_no);
	}
	public void updateRevS(Review dto) {
		dao.updateRev(dto);
	}
	public ArrayList<Review> calcultotalS(int h_no) {
		return dao.calcultotal(h_no);
	}
}
