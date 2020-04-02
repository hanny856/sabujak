package sabujak.domain;

import java.sql.Date;

public class Sabujak {
	private int c_no;
	private String email_fk_pk;
	private String c_sub;
	private String c_cont;
	private Date c_date;
	private int c_view;
	private String c_ofname;
	private String c_fname;
	public String getC_fname() {
		return c_fname;
	}

	public void setC_fname(String c_fname) {
		this.c_fname = c_fname;
	}

	public Sabujak(int c_no, String c_sub, String c_cont, String email_fk_pk, Date c_date, int c_view) {
		this.c_no=c_no;
		this.c_sub=c_sub;
		this.c_cont=c_cont;
		this.email_fk_pk=email_fk_pk;
		this.c_date=c_date;
		this.c_view=c_view;
	}

	public Sabujak(int c_no, String email_fk_pk, String c_sub, Date c_date, int c_view) {
		this.c_no=c_no;
		this.email_fk_pk=email_fk_pk;
		this.c_sub=c_sub;
		this.c_date=c_date;
		this.c_view=c_view;
	}

	public Sabujak(String c_sub, String email_fk_pk, Date c_date, int c_view, String c_cont, String c_ofname,long c_fsize) {
		this.c_sub=c_sub;
		this.c_cont=c_cont;
		this.email_fk_pk=email_fk_pk;
		this.c_date=c_date;
		this.c_view=c_view;

	}

	public Sabujak(int c_no, String c_sub, String email_fk_pk, Date c_date, int c_view, String c_cont,String c_ofname,String c_fname) {
		this.c_no=c_no;
		this.c_sub=c_sub;
		this.email_fk_pk=email_fk_pk;
		this.c_date=c_date;
		this.c_view=c_view;
		this.c_cont=c_cont;
		this.c_ofname=c_ofname;
		this.c_fname=c_fname;
	}

	public Sabujak(String email_fk_pk, String c_sub, String c_cont, String c_fname, String c_ofname) {
		this.email_fk_pk=email_fk_pk;
		this.c_sub=c_sub;
		this.c_cont=c_cont;
		this.c_fname=c_fname;
		this.c_ofname=c_ofname;
	}

	public Sabujak(int c_no, String c_sub, String c_cont, String newfname, String newofname) {
		this.c_no=c_no;
		this.c_sub=c_sub;
		this.c_cont=c_cont;
		this.c_fname=newfname;
		this.c_ofname=newofname;
	}

	public Sabujak(int c_no, String c_sub, String c_cont) {
		this.c_no=c_no;
		this.c_sub=c_sub;
		this.c_cont=c_cont;
	}

	public Sabujak(int c_no, String email_fk_pk, String m_pw, String m_name, String m_call, String m_addr) {
		this.c_no=c_no;
		this.email_fk_pk=email_fk_pk;
		
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getEmail_fk_pk() {
		return email_fk_pk;
	}

	public void setEmail_fk_pk(String email_fk_pk) {
		this.email_fk_pk = email_fk_pk;
	}

	public String getC_sub() {
		return c_sub;
	}

	public void setC_sub(String c_sub) {
		this.c_sub = c_sub;
	}

	public String getC_cont() {
		return c_cont;
	}

	public void setC_cont(String c_cont) {
		this.c_cont = c_cont;
	}

	public Date getC_date() {
		return c_date;
	}

	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}

	public int getC_view() {
		return c_view;
	}

	public void setC_view(int c_view) {
		this.c_view = c_view;
	}

	public String getC_ofname() {
		return c_ofname;
	}

	public void setC_ofname(String c_ofname) {
		this.c_ofname = c_ofname;
	}
}

