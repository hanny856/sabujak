package sabujak.domain;

import java.sql.Date;

public class Hboard {
	private int h_no;
	private int hcode_fk;
	private String h_sub;
	private String h_cont;
	private int h_like;
	private Date h_date;
	private int h_view;
	private String email_fk;
	private String fname;
	private String ofname;
	public Hboard() {
	}
	public Hboard(int h_no, int hcode_fk, String h_sub, String h_cont, int h_like, Date h_date, int h_view,
			String email_fk, String fname, String ofname) {
		super();
		this.h_no = h_no;
		this.hcode_fk = hcode_fk;
		this.h_sub = h_sub;
		this.h_cont = h_cont;
		this.h_like = h_like;
		this.h_date = h_date;
		this.h_view = h_view;
		this.email_fk = email_fk;
		this.fname = fname;
		this.ofname = ofname;
	}
	public int getH_no() {
		return h_no;
	}
	public void setH_no(int h_no) {
		this.h_no = h_no;
	}
	public int getHcode_fk() {
		return hcode_fk;
	}
	public void setHcode_fk(int hcode_fk) {
		this.hcode_fk = hcode_fk;
	}
	public String getH_sub() {
		return h_sub;
	}
	public void setH_sub(String h_sub) {
		this.h_sub = h_sub;
	}
	public String getH_cont() {
		return h_cont;
	}
	public void setH_cont(String h_cont) {
		this.h_cont = h_cont;
	}
	public int getH_like() {
		return h_like;
	}
	public void setH_like(int h_like) {
		this.h_like = h_like;
	}
	public Date getH_date() {
		return h_date;
	}
	public void setH_date(Date h_date) {
		this.h_date = h_date;
	}
	public int getH_view() {
		return h_view;
	}
	public void setH_view(int h_view) {
		this.h_view = h_view;
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
