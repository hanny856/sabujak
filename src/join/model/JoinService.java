package join.model;

import join.domain.Join;
import join.model.JoinDAO;

public class JoinService {
	private JoinDAO dao;
	private static final JoinService instance = new JoinService(); 
	private JoinService() {
		dao = new JoinDAO();
	}
	public static JoinService getInstance() {
		return instance;
	}
	public boolean memberADDS(Join join) {
		boolean b1=dao.memberADD(join);
		if(b1) {
			return true;
		}else {
			return false;
		}
	}

}
