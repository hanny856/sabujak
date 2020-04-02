package sabujak.domain;

import java.sql.Date;

public class Fileboard {
	private String f_name;
	private String f_ofname;
	private long f_size;
	private Date f_date;
	private long h_no_fk;
	public Fileboard() {
	}
	public Fileboard(String f_name, String f_ofname, long f_size, Date f_date, long h_no_fk) {
		super();
		this.f_name = f_name;
		this.f_ofname = f_ofname;
		this.f_size = f_size;
		this.f_date = f_date;
		this.h_no_fk = h_no_fk;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_ofname() {
		return f_ofname;
	}
	public void setF_ofname(String f_ofname) {
		this.f_ofname = f_ofname;
	}
	public long getF_size() {
		return f_size;
	}
	public void setF_size(long f_size) {
		this.f_size = f_size;
	}
	public Date getF_date() {
		return f_date;
	}
	public void setF_date(Date f_date) {
		this.f_date = f_date;
	}
	public long getH_no_fk() {
		return h_no_fk;
	}
	public void setH_no_fk(long h_no_fk) {
		this.h_no_fk = h_no_fk;
	}
	
	
	
}
