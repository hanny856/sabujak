conn system/java0909;
create user manager identified by java;
grant CONNECT, RESOURCE to manager;
conn manager/java;					

drop sequence N_NO_SEQ;
drop table NOTICE;

CREATE TABLE NOTICE(
   N_NO number constraint NOTICE_PK primary key,      --글번호
   N_SUB varchar2(60) constraint N_SUB_NN not null,   --제목
   N_CONT varchar2(2000)constraint N_CONT_NN not null,   --내용
   N_DATE DATE default sysdate,            --등록일
   EMAIL_FK varchar2(60),
   FNAME varchar2(100), --파일이름
   OFNAME varchar2(100), --파일 고유이름 
   constraint MEMBER_TO_NOTICE_FK foreign key(EMAIL_FK) references MEMBER(EMAIL) on delete cascade,   --이메일
   COL5 number(2),                     --예비컬럼
   COL7 number(2)                     --예비컬럼
); 
  
--공지사항 시퀸스 번호 
create sequence N_NO_SEQ start with 0 increment by 1 minvalue 0 nocache;

insert into NOTICE values(N_NO_SEQ.nextval,'공지사항', '사부작 커뮤니티에 오신걸 환영합니다.', sysdate, 'hong@naver.com',FNAME, OFNAME, null, null);
