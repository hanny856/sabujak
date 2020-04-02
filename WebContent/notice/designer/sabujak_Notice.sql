conn system/java0909;
create user manager identified by java;
grant CONNECT, RESOURCE to manager;
conn manager/java;					

drop sequence N_NO_SEQ;
drop table NOTICE;

CREATE TABLE NOTICE(
   N_NO number constraint NOTICE_PK primary key,      --�۹�ȣ
   N_SUB varchar2(60) constraint N_SUB_NN not null,   --����
   N_CONT varchar2(2000)constraint N_CONT_NN not null,   --����
   N_DATE DATE default sysdate,            --�����
   EMAIL_FK varchar2(60),
   FNAME varchar2(100), --�����̸�
   OFNAME varchar2(100), --���� �����̸� 
   constraint MEMBER_TO_NOTICE_FK foreign key(EMAIL_FK) references MEMBER(EMAIL) on delete cascade,   --�̸���
   COL5 number(2),                     --�����÷�
   COL7 number(2)                     --�����÷�
); 
  
--�������� ������ ��ȣ 
create sequence N_NO_SEQ start with 0 increment by 1 minvalue 0 nocache;

insert into NOTICE values(N_NO_SEQ.nextval,'��������', '����� Ŀ�´�Ƽ�� ���Ű� ȯ���մϴ�.', sysdate, 'hong@naver.com',FNAME, OFNAME, null, null);
