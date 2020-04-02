package reply.model;

public class ReplySQL {
	final static String LIST_PAGE="select * from(select ROWNUM rnum , aa.* from (select * from COMMU_RE order by CRE_NO desc) aa) where rnum>? and rnum<=? and c_no_fk=?";
	final static String INSERT="insert into COMMU_RE(CRE_NO,CRE_CONT,C_NO_FK,EMAIL_FK,GROUP1,LINE,SPACING,COL2) values(CRE_NO_SEQ.nextval,?,?,?,1,0,0,1)";
	final static String DEL="delete from COMMU_RE where CRE_NO=?";
	final static String COUNT = "select count(CRE_NO) from COMMU_RE";
}
//select * from(select ROWNUM rnum , aa.* from (select * from COMMU_RE order by CRE_NO desc) aa) where rnum>0 and rnum<=3 and c_no_fk=2
