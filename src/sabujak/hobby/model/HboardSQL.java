package sabujak.hobby.model;

class HboardSQL {
	static final String LIST = "select * from (select rownum rnum , aa.* from (select * from hboard order by H_NO desc)aa) where rnum > ? and rnum <= ?";
	
	static final String COUNT = "select count(h_no) from Hboard";
	static final String INSERT= "insert into HBOARD HBOARD(H_NO, HCODE_FK, H_SUB,H_CONT, EMAIL_FK, FNAME, OFNAME) values(H_NO_SEQ.nextval,?,?,?,?,?,?)";
	static final String CONTENT = "select * from HBOARD where H_NO=?";
	static final String DEL = "delete from HBOARD where H_NO=?";
	static final String UPDATE = "update HBOARD set hcode_fk=?, h_sub=?, h_cont=? , fname=?, ofname=? where h_no=?";
	static final String VIEWUP = "update HBOARD set h_view =? where h_no=?";
	static final String LISTVIEW ="select * from (select rownum rnum , aa.* from (select * from hboard order by H_VIEW desc)aa) where rnum > ? and rnum <= ?";
	static final String LISTREV ="select * from (select rownum rnum , aa.* from (select * from hboard order by H_VIEW desc)aa) where rnum > ? and rnum <= ?";
}
