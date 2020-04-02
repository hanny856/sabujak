package sabujak.model;

import java.util.ArrayList;

import sabujak.domain.Sabujak;
import sabujak.model.SabujakDAO;
import sabujak.model.SabujakService;
import sabujak.vo.ListResult;

public class SabujakService {
	private SabujakDAO dao;
	private static final SabujakService instance = new SabujakService(); 
	private SabujakService() {
		dao = new SabujakDAO();
	}
	public static SabujakService getInstance() {
		return instance;
	}
	
	public ListResult getListResult(int currentPage, int pageSize) {
		ArrayList<Sabujak> list = dao.list(currentPage, pageSize);
		long totalCount = dao.getTotalCount();
		ListResult r = new ListResult(currentPage, totalCount, pageSize, list);
		return r;
	}

	public void insertS(Sabujak sabujak) {
		dao.insert(sabujak);
	}
	public Sabujak getBoardS(int c_no) {
		return dao.getBoard(c_no);
	}

	public void delS(int c_no) {
		dao.del(c_no);
	}
	public void getView(int c_no) {
		dao.getView(c_no);
	}
	public void upS(Sabujak sabujak) {
		dao.up(sabujak);
	}
	public void updateS(Sabujak sabujak) {
		dao.update(sabujak);
	}
}
