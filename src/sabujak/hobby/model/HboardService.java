package sabujak.hobby.model;

import java.util.ArrayList;

import sabujak.domain.Hboard;
import sabujak.hobby.vo.ListResult;

public class HboardService {
	private HboardDAO dao;
	private static final HboardService instance = new HboardService();
	private HboardService() {
		dao = new HboardDAO();
	}
	public static HboardService getInstance() {
		return instance;
	}
	public ListResult getListResult(int currentPage, int pageSize) {
		ArrayList<Hboard> list = dao.list(currentPage, pageSize);
		long totalCount = dao.getTotalCount();
		ListResult r = new ListResult(currentPage, totalCount, pageSize, list);
		
		return r;
	}
	
	public ListResult getListResultView(int currentPage, int pageSize) {
		ArrayList<Hboard> listNew = dao.listView(currentPage, pageSize);
		long totalCount = dao.getTotalCount();
		ListResult r = new ListResult(currentPage, totalCount, pageSize, listNew);
		
		return r;
	}
	public void insertS(Hboard dto) {
		dao.insertB(dto);
	}
	public Hboard contentS(int h_no) {
		return dao.content(h_no);
	}
	public void delS(int h_no){
		dao.del(h_no);
	}
	public void updateS(Hboard dto) {
		dao.update(dto);
	}
	public void viewUpS(int h_no, int h_view) {
		dao.viewUp(h_no,h_view);
	}
}
