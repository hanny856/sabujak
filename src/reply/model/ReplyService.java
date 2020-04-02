package reply.model;

import java.util.ArrayList;

import reply.model.ReplyDAO;
import reply.model.ReplyService;
import sabujak.domain.DTO;
import sabujak.vo.Reply;

public class ReplyService {
	private ReplyDAO dao;
	private static final ReplyService instance = new ReplyService(); 
	private ReplyService() {
		dao = new ReplyDAO();
	}
	public static ReplyService getInstance() {
		return instance;
	}
	public Reply getReply(int currentPage, int pageSize,int c_no) {
		ArrayList<DTO> list = dao.list(currentPage, pageSize,c_no);
		long totalCount = dao.getTotalCount();
		Reply r = new Reply(currentPage, totalCount, pageSize, list);
		return r;
	}
	public void insertS(DTO dto) {
		dao.insert(dto);
	}
	public void delS(int cre_no) {
		dao.del(cre_no);
	}

}
