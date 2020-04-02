package sabujak.domain;

import java.sql.Date;

public class DTO {
	private int cre_no;
	private String cre_cont;
	private Date cre_date;
	private int c_no_fk;
	private String email_fk;
	
	public DTO(int cre_no, String cre_cont, Date cre_date,int c_no_fk, String email_fk) {
		this.cre_no=cre_no;
		this.cre_cont=cre_cont;
		this.cre_date=cre_date;
		this.c_no_fk=c_no_fk;
		this.email_fk=email_fk;
	}

	public DTO(String email_fk, String cre_cont, int c_no_fk) {
		this.email_fk=email_fk;
		this.cre_cont=cre_cont;
		this.c_no_fk=c_no_fk;
	}

	public int getCre_no() {
		return cre_no;
	}

	public void setCre_no(int cre_no) {
		this.cre_no = cre_no;
	}

	public String getCre_cont() {
		return cre_cont;
	}

	public void setCre_cont(String cre_cont) {
		this.cre_cont = cre_cont;
	}

	public Date getCre_date() {
		return cre_date;
	}

	public void setCre_date(Date cre_date) {
		this.cre_date = cre_date;
	}

	public int getC_no_fk() {
		return c_no_fk;
	}

	public void setC_no_fk(int c_no_fk) {
		this.c_no_fk = c_no_fk;
	}

	public String getEmail_fk() {
		return email_fk;
	}

	public void setEmail_fk(String email_fk) {
		this.email_fk = email_fk;
	}
	
}
