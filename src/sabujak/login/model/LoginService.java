package sabujak.login.model;

import sabujak.domain.Member;

public class LoginService {
	private LoginDAO dao;
	private static final LoginService instance = new LoginService();
	private Member m;
	private LoginService() {
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	public int checkMember(String email, String pwd) {
		Member m = dao.getMember(email);
		if(m==null) {
			return LoginSet.NO_ID;
		}else {
			String psdDb = m.getM_pw() ;
			if(psdDb != null) psdDb = psdDb.trim();
			if(!pwd.equals (psdDb)) {
				return LoginSet.NO_PWD;
			}else {
				return LoginSet.PASS;
			}
		}
	}
	public Member getMemberS(String email) {
		Member m = dao.getMember(email);
		m.setM_pw("");
		return m;
	}
}
