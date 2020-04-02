package sabujak.review.model;

class ReviewSQL {
	static final String LIST_REV = "select * from REVIEW where h_no=? order by REV_NO desc";
	static final String LIST = "select * from (select rownum rnum, aa.*from (select * from REVIEW where h_no=? order by REV_NO desc)aa) where rnum>? and rnum<=?";
	static final String COUNT = "select count(rev_no) from REVIEW where H_NO=?";
	static final String INSERT = "insert into REVIEW(REV_NO,H_NO,REV_GOOD,REV_CONT,EMAIL_FK) values(REV_NO_SEQ.nextval,?,?,?,?)";
	static final String DEL = "delete from REVIEW where REV_NO=?";
	static final String CONTENT = "select * from REVIEW where REV_NO=? and H_NO=?";
	static final String UPDATE = "update REVIEW set rev_good=?, rev_cont=? where REV_NO=? and H_NO=?";
	static final String REVTOTAL = "select * from REVIEW where h_no=?";
}
