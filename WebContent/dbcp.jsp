<%@ page language="java" contentType="text/html; charset=utf-8" import="javax.naming.*,javax.sql.*,java.sql.*"%>
<!doctype html>
<html>
 <head>
	<meta charset="UTF-8">
	<title>DBCP test</title>
 </head>
 <body>
  <center>
	<h1>DBCP test</h1>
	<p>
	<%
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection con = ds.getConnection();
	%>
	커넥션 객체(by DBCP) : <%=con.hashCode()%>
	</p>
  </center>
 </body>
</html>
