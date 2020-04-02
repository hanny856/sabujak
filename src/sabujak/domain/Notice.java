package sabujak.domain;

import java.sql.Date;

public class Notice {
	
	private int n_no;
	private String n_sub;
	private String n_cont;
	private	Date n_date;
	private String email_fk;
	private String fname;
	private String ofname;

	public Notice(int n_no, String n_sub, String n_cont, Date n_date, String email_fk, String fname, String ofname) {
		this.n_no = n_no;
		this.n_sub = n_sub;
		this.n_cont = n_cont;
		this.n_date = n_date;
		this.email_fk = email_fk;
		this.fname = fname;
		this.ofname = ofname;
	}
	public Notice(int n_no, String n_sub, Date n_date, String email_fk) {
		this.n_no = n_no;
		this.n_sub = n_sub;
		this.n_date = n_date;
		this.email_fk = email_fk;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public String getN_sub() {
		return n_sub;
	}
	public void setN_sub(String n_sub) {
		this.n_sub = n_sub;
	}
	public String getN_cont() {
		return n_cont;
	}
	public void setN_cont(String n_cont) {
		this.n_cont = n_cont;
	}
	public Date getN_date() {
		return n_date;
	}
	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}
	public String getEmail_fk() {
		return email_fk;
	}
	public void setEmail_fk(String email_fk) {
		this.email_fk = email_fk;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getOfname() {
		return ofname;
	}
	public void setOfname(String ofname) {
		this.ofname = ofname;
	}
	
}
