package join.domain;

public class Join {
	private int m_no;
	private String email_fk_pk;
	private String m_pw;
	private String m_name;
	private String m_call;
	private String m_addr;
	
	public Join(int m_no, String email_fk_pk, String m_pw, String m_name, String m_call, String m_addr) {
		this.m_no=m_no;
		this.email_fk_pk=email_fk_pk;
		this.m_pw=m_pw;
		this.m_name=m_name;
		this.m_call=m_call;
		this.m_addr=m_addr;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getEmail_fk_pk() {
		return email_fk_pk;
	}

	public void setEmail_fk_pk(String email_fk_pk) {
		this.email_fk_pk = email_fk_pk;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_call() {
		return m_call;
	}

	public void setM_call(String m_call) {
		this.m_call = m_call;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

}
