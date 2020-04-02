package sabujak.domain;

import java.sql.Date;

public class Member {
	private String email;
	private int m_no;
	private String m_pw;
	private String m_name;
	private String m_call;
	private String m_addr;
	private Date m_date;
	private int m_kind;
	public Member() {
	}
	public Member(String email, int m_no, String m_pw, String m_name, String m_call, String m_addr, Date m_date,
			int m_kind) {
		this.email = email;
		this.m_no = m_no;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_call = m_call;
		this.m_addr = m_addr;
		this.m_date = m_date;
		this.m_kind = m_kind;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
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
	public Date getM_date() {
		return m_date;
	}
	public void setM_date(Date m_date) {
		this.m_date = m_date;
	}
	public int getM_kind() {
		return m_kind;
	}
	public void setM_kind(int m_kind) {
		this.m_kind = m_kind;
	}
	
	
}
