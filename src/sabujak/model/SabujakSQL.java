package sabujak.model;

class SabujakSQL {
	final static String LIST_PAGE = "select C_NO,C_SUB,EMAIL_FK_PK,C_DATE,C_VIEW from (select ROWNUM rnum , aa.* from (select * from COMMU order by C_NO desc) aa) where rnum>? and rnum<=?";
	final static String COUNT = "select count(C_NO) from COMMU";
	final static String CONTENT = "select C_SUB,EMAIL_FK_PK,C_DATE,C_VIEW,C_CONT,C_OFNAME,C_FNAME from COMMU where C_NO=?";
	final static String GETVIEW= "select C_VIEW from COMMU where C_NO=?";
	final static String SETVIEW="update COMMU set C_VIEW=? where C_NO=?";
	final static String DEL="delete from COMMU where C_NO=?";
	final static String INSERT = "insert into COMMU(C_NO,EMAIL_FK_PK,C_SUB,C_CONT,C_FNAME,C_OFNAME) values(CNO_SEQ.nextval,?,?,?,?,?)";
	final static String UP= "update COMMU set C_SUB=?,C_CONT=? where c_no=?";
	final static String UPDATE= "update COMMU set C_SUB=?,C_CONT=?,C_FNAME=?,C_OFNAME=? where C_NO=?";
}
