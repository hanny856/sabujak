package notice.model;

import java.util.ArrayList;

import sabujak.domain.Notice;
import sabujak.vo.NoticeR;

public class NoticeService {
	private NoticeDAO dao;
	private static final NoticeService instance = new NoticeService(); 
	private NoticeService() {
		dao = new NoticeDAO();
	}
	public static NoticeService getInstance() {
		return instance;
	}
	public NoticeR getListResult(int currentPage, int pageSize) {
		ArrayList<Notice> list = dao.list(currentPage, pageSize);
		long totalCount = dao.getTotalCount();
		NoticeR r = new NoticeR(currentPage, totalCount, pageSize, list);
		return r;
	}
	public void insertS(Notice notice) {
		dao.insert(notice);
	}
	public Notice getNoticeS(int n_no) {
		return dao.getNotice(n_no);
	}
	public void updateS(Notice notice) {
		dao.update(notice);
	}
	public void delS(int n_no) {
		dao.del(n_no);
	}
}
