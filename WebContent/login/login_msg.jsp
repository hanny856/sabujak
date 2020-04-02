<%@page contentType="text/html;charset=utf-8" import ="sabujak.login.model.LoginSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
	if(${rCode}==<%=LoginSet.NO_ID%>){
		alert("없는 아이디 입니다.");
	}else if(${rCode}==<%=LoginSet.NO_PWD%>){
		alert("비밀번호가 틀렸습니다.");
	}
	location.href="../index.do";
</script>