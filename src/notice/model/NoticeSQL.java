package notice.model;

class NoticeSQL {
	final static String INSERT ="insert into NOTICE values(N_NO_SEQ.nextval, ?, ?, SYSDATE, ?, ?, ?,0,0)";
	final static String CONTENT = "select * from NOTICE where n_no=?";
	final static String UPDATE = "update NOTICE set  email_fk=?, n_sub=?, n_cont=?, fname=?, ofname=? where n_no=?";
	final static String DEL = "delete from NOTICE where n_no=?";
	final static String COUNT = "select count(n_no) from NOTICE";
	final static String LIST_PAGE ="select * from (select ROWNUM rnum , aa.* from (select * from NOTICE order by n_no desc) aa)" + 
			" where rnum>? and rnum<=?";
}
