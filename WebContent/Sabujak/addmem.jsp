<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
	<!-- 
		 ###
		####
	   ## ##
	  ##  ##
	 #########	
	 	  ##
	 	  ##
	-->
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta name="msapplication-TileColor" content="#000000">
	<meta name="msapplication-TileImage" content="/assets/images/Favicon_W144x144.png">
	<meta name="msapplication-config" content="/browserconfig.xml">
	<meta name="robots" content="NOODP">
	<meta name="robots" content="NOYDIR">
	<meta name="description" content="Discover a wide range of high quality products from Sony and the technology behind them, get instant access to our store and Entertainment Network.">
	<meta name="og:image" content="//sonyglobal.scene7.com/is/image/gwtprod/sonyview1?fmt=png&amp;wid=1200">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title> 사부작 | 회원가입</title>
	
	<link rel="shortcut icon" href="//www.sony.co.uk/assets/images/Favicon_144x144.png">
	<link rel="apple-touch-icon" href="//www.sony.co.uk/assets/images/apple-touch-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="72x72" href="//www.sony.co.uk/assets/images/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="//www.sony.co.uk/assets/images/apple-touch-icon-114x114.png">
	<link href="//image.sony.co.kr/sonySwt/css/normalize.css" media="all" rel="stylesheet" type="text/css">
	<link href="//image.sony.co.kr/sonySwt/css/swiper.min.css" media="all" rel="stylesheet" type="text/css">
	<link href="//image.sony.co.kr/sonySwt/css/layout.css?d=1067" media="all" rel="stylesheet" type="text/css">

<style type="text/css">
	#eds{color:#282828;
	}
	#send{ width: 200px; border:none; height:30px; border-radius:3px; background-color:#e19f87; color:white;}
	#btn_div {width:35%; margin:0 auto; padding-top:10px;}
	#reset{border:none; width: 200px; height:30px; border-radius:3px;}
</style>
<!-- Global site tag (gtag.js) - Google Analytics 2017-11-20 -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-60674205-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-60674205-1');
</script>
<!-- //Global site tag (gtag.js) - Google Analytics 2017-11-20 -->
</head>
<body>

<script src="../js/trim.js"></script>

<script>
var submitCheck=false;

function join(){
	var emailId = f.emailId.value;
	emailId=trim(emailId);
	var domain= f.domain.value;
	domain= trim(domain);
	var name = f.name.value;
	name = trim(name);
	var phone = f.phone.value;
	phone = trim(phone);
	var password = f.password.value;
	password = trim(password);
	var passwordCheck = f.passwordCheck.value;
	passwordCheck = trim(passwordCheck);
	if(!isEmpty(emailId, "이메일 주소를 입력하시기 바랍니다.", "emailId")) return;
	if(!isEmpty(domain, "이메일 도메인을 선택하십시오.", "domain")) return;
	if(!isEmpty(name, "성함을 입력하시기 바랍니다.", "name")) return;
	if(!isEmpty(phone, "성함을 입력하시기 바랍니다.", "phone")) return;
	if(!isEmpty(password, "비밀번호를 입력하십시오.", "password")) return;
	if(!checkPasswordReg(password, 10, 15, "비밀번호는 10자 이상 15자 이하 숫자/문자 조합으로 입력하십시오", "password")) return;
	if(!isEmpty(passwordCheck, "비밀번호를 재 입력하십시오.", "passwordCheck")) return;
	if(!checkPasswordReg(passwordCheck, 10, 15, "비밀번호는 10자 이상 15자 이하 숫자/문자 조합으로 입력하십시오", "passwordCheck")) return;
	if(password != passwordCheck){
			alert("비밀번호가 일치하지 않습니다. 다시 입력하십시오.");
			f.passwordCheck.focus();
			return;
	}
	
	f.submit();
}


function isEmpty(strValue, message, inputName){
	if(strValue == ""){
		alert(message);
		$("input[name="+inputName+"]").focus();
		return false;
	}
	return true;
}

function checkPasswordReg(strPassword, minLength, maxLength, message, inputName){
	var reg = /^.*(?=.{10,15})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	if(!reg.test(strPassword)){
		alert(message);
		$("input[name="+inputName+"]").focus();
		return false;
	}
	return true;
}

function changeDomain(){
	var domain = f.domainSelect.value;
	if(domain == "direct"){
		f.domain.value = "";
		f.domain.readOnly = false;
		f.domain.focus();
	}else{
		f.domain.value = domain;
		f.domain.readOnly = true;
	}

}

</script>
<!-- SECTION -->
<section class="m-section">
	<!-- contents -->
	<div class="contents">
		<div class="member ty1">
			<div class="box-ty2">
				<h2 class="co-tit">사부작 회원가입</h2>
			</div>
			<div class="m-box-content">
			<form name="f" action="join.do?m=member" method="post">			
				<div class="info-box-ty2">
					<table class="tb-ty3">
						<colgroup>
							<col class="col-1">
							<col class="col-2">
						</colgroup>
						<tbody>
						
							<tr>
							<span id="eds">  | * 는 필수 입력 사항 입니다  ! ( Email은 아이디로 사용됩니다. ) |  </span>
								<th scope="row"><div>Email<span>*</span></div></th>
								<td class="email">
									<input type="text" name="emailId" class="ipt-ty1 onlyEn" maxlength="30" pattern="[A-Za-z0-9]*">
									<span class="at-mark">@</span>
									<input type="text" name="domain" class="ipt-ty1">
									<select name="domainSelect" class="sel-ty1" onchange="changeDomain();" title="이메일 선택">
										<option value="direct">직접입력</option>
										<option value="naver.com">naver.com</option>
										<option value="daum.net">daum.net</option>
										<option value="hotmail.com">hotmail.com</option>
										<option value="nate.com">nate.com</option>
										<option value="paran.com">paran.com</option>
										<option value="empas.com">empas.com</option>
										<option value="dreamwiz.com">dreamwiz.com</option>
										<option value="lycos.co.kr">lycos.co.kr</option>
										<option value="korea.com">korea.com</option>
										<option value="gmail.com">gmail.com</option>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row"><div>Password  <span>*</span></div></th>
								<td class="w-other-ty1"><input type="password" name="password" class="ipt-ty1" placeholder="영문/ 숫자조합 10~15자리 미만" maxlength="15">
							</tr> 
							<tr>
								<th scope="row"><div>Check Password   <span>*</span></div></th>
								<td class="w-other-ty1"><input type="password" name="passwordCheck" class="ipt-ty1" maxlength="15"></td>
								
							</tr>
							<tr>
								<th scope="row"><div>Name  <span>*</span></div></th>
								<td class="w-other-ty1"><input type="text" name="name" class="ipt-ty1" maxlength="20"></td>
							</tr>
							<tr>
								<th scope="row"><div>Phone Number  <span>*</span></div></th>
								<td class="w-other-ty1"><input type="text" name="phone" class="ipt-ty1" placeholder="(ㅡ 없이 입력 해주세요 ! )" maxlength="11"></td>
							</tr>
							<tr>
								<th scope="row"><div>Address</div></th>
								<td class="w-other-ty1"><input type="text" name="addr" class="ipt-ty1" maxlength="200"></td>
							</tr>
						</tbody>
					</table>
				</div>
					<div id="btn_div">
			     <input type="button" value="전송" onclick="join()" id="send"/>
				 <input type="reset" value="취소" id="reset" />
			</div>
			</form>
		
		</div>
	</div>
	<!-- //contents -->
</section>
<!-- //SECTION -->

</html>
