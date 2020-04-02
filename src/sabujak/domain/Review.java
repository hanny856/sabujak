package sabujak.domain;

import java.sql.Date;

public class Review {
	private int rev_no;
	private int h_no;
	private int rev_good;
	private String rev_sub;
	private String rev_cont;
	private Date rev_date;
	private String email_fk;
	
	public Review() {
	}
	public Review(int rev_no, int h_no, int rev_good, String rev_sub, String rev_cont, Date rev_date, String email_fk){
		this.rev_no = rev_no;
		this.h_no = h_no;
		this.rev_good = rev_good;
		this.rev_sub = rev_sub;
		this.rev_cont = rev_cont;
		this.rev_date = rev_date;
		this.email_fk = email_fk;
	}
	public int getRev_no() {
		return rev_no;
	}
	public void setRev_no(int rev_no) {
		this.rev_no = rev_no;
	}
	public int getH_no() {
		return h_no;
	}
	public void setH_no(int h_no) {
		this.h_no = h_no;
	}
	public int getRev_good() {
		return rev_good;
	}
	public void setRev_good(int rev_good) {
		this.rev_good = rev_good;
	}
	public String getRev_sub() {
		return rev_cont;
	}
	public void setRev_sub(String rev_sub) {
		this.rev_sub = rev_sub;
	}
	public String getRev_cont() {
		return rev_cont;
	}
	public void setRev_cont(String rev_cont) {
		this.rev_cont = rev_cont;
	}
	public Date getRev_date() {
		return rev_date;
	}
	public void setRev_date(Date rev_date) {
		this.rev_date = rev_date;
	}
	public String getEmail_fk() {
		return email_fk;
	}
	public void setEmail_fk(String email_fk) {
		this.email_fk = email_fk;
	}
	
}
