<%@page contentType="text/html;charset=utf-8" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <title> 사부작 | 로그인 </title>
   
   <link rel="shortcut icon" href="//www.sony.co.uk/assets/images/Favicon_144x144.png">
   <link rel="apple-touch-icon" href="//www.sony.co.uk/assets/images/apple-touch-icon-57x57.png">
   <link rel="apple-touch-icon" sizes="72x72" href="//www.sony.co.uk/assets/images/apple-touch-icon-72x72.png">
   <link rel="apple-touch-icon" sizes="114x114" href="//www.sony.co.uk/assets/images/apple-touch-icon-114x114.png">
   <link href="//image.sony.co.kr/sonySwt/css/normalize.css" media="all" rel="stylesheet" type="text/css">
   <link href="//image.sony.co.kr/sonySwt/css/swiper.min.css" media="all" rel="stylesheet" type="text/css">
   <script src="../js/trim.js"></script>

<script language="javascript">
	function check(){
		var emailval = f.email.value;
		emailval = trim(emailval);
		if(emailval.length == 0){
			alert("이메일을 넣어주세요");
			f.email.value = "";
			f.email.focus();
			return false;
		}else{
			pass = checkByteLen(emailval, 50);
			if(!pass){
				alert("이메일이 너무 길어요");
				f.email.focus();
				return false;
			}
		}
		
		var pwdval = f.pwd.value;
		pwdval = trim(pwdval);
		if(pwdval.length == 0){
			alert("비밀번호를 넣어주세요");
			f.pwd.value = "";
			f.pwd.focus();
			return false;
		}else{
			pass = checkByteLen(pwdval, 30);
			if(!pass){
				alert("비밀번호가 너무 길어요");
				f.pwd.focus();
				return false;
			}
		}
	
		f.submit();
	}
	
	function checkByteLen(str, size){
	    var byteLen = getByteLen(str);
		if(byteLen <= size){
			return true;
		}else{
			return false;
		}
	}
	function getByteLen(str){
	   return str.replace(/[\0-\x7f]|([0-\u07ff]|(.))/g,"$&$1$2").length;
	}
	
	function enterCheck(elm){
		if(event.keyCode == 13){
			if(elm == f.email){
				f.pwd.focus();
			}else{
				check();
			}
		}
	}
</script>
<style>
	*{font:normal 12px '나눔바른고딕';}
	a{text-decoration:none;}
	#top ul{margin-top:40px; margin-bottom:20px;}
	.logo{float:left; padding-left:32%;}
	.logo img{padding-right:15px;}
	.loginH1{padding-top:30px; font-weight:bold; font-size:16px;}
	#btn{width:100%; align:center;}
	#btn input{margin:0 auto; height:40px; text-align:center; font: normal 15px '나눔바른고딕'; width:260px; background-color:#e19f87; color:white; border:none;}
	#login_form{align:center; margin: 0 auto; width:400px;}
	#login_form tr td{text-align:center; padding:5px;}
	#login_form .id_pw{width:260px; height:30px;}
	.box-ty1{width:35%; align:center; background-color:#f4f4f4; margin:0 auto; padding-top: 20px; height:200px;}
	ul{list-style: none; /*overflow: hidden;*/}
	#login_form .btn-change{background-color:#bbb; color:white; padding:5px 10px;}
</style>
<!-- SECTION -->
   <!-- contents -->
   <div class="contents">
      <div class="member ty1">
      	<div id="top">
      		<ul>
      			<li class="logo"><img src="../images_sabujak/logo_top.png" alt="사부작로고"/></li>
      			<li class="loginH1"><h1>사부작 로그인</h1></li>
      			<li class="loginli">사부작을 이용해주셔서 감사합니다 ^v^<br>로그인 시 등록하신 이메일 주소(이메일 아이디)를
      			<br/> <b>모두</b> 입력하셔야 합니다. (예: sabujak@sabujak.co.kr)</li>
      		</ul>
         	<p class="logo"></p>
            
      </div>
         <div class="m-box-content box-ty1" width="50%">
        
            <input type="hidden" name="returnURL" value=""/>
            <input type="hidden" name='sonyStoreCheck' />
            <input type="hidden" name='emailId' />
            <input type="hidden" name='domain' />
            <input type="hidden" name='snsInfo' />
            <input type="hidden" name='snsCustomerId' />
         
           
            <table id="login_form">
             <form name="f" method="post" action ="login.do?m=check">   
            <tr>
                <td>
                   <input class ="id_pw" type="text" rows=10 cols=10  name="email" onkeydown="enterCheck(this)" placeholder="ID를 입력해주세요">
                </td>
            </tr>
            <tr>
            	<td> 
            		<input class ="id_pw" type="password" name="pwd" onkeydown="enterCheck(this)" placeholder="PASSWORD를 입력해주세요">
            	</td>
            </tr>
            
            <tr>
            	<td>
            	   <div id="btn" >
              		 <input type="button" value="전송" onclick="check()"/>
         		 </div>
            	</td>
            </tr>
            <tr>
            	<td>
            		<a href="#" class="btn-change">아이디 찾기</a>
                 	<a href="#" class="btn-change">비밀번호 찾기</a>
                  	<a href="../Sabujak/sabujak.do?m=addmem" class="btn-change">회원가입</a>
            	</td>
            </tr>
             </form>
            </table>
         </div>
      </div>
   </div>

<script type="text/javascript" src="//image.sony.co.kr/omniture/real/scs_code_2017.js"></script>
<!-- /************* Omniture code tagging **************/ -->
</div>
</html>