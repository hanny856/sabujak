<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- include libraries(jQuery, bootstrap) -->
<html>
	<head>
		<meta charset="UTF-8">
		<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
		<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

	<!-- include summernote css/js -->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	  $('#summernote').summernote({
			placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
	</script>
	
	  <script type="text/javascript">
	$(document).ready(function(){
		$('.navi_sub').hide();
		$('#navi_bg').hide();	

		$('.lnb,#navi_bg').mouseenter(function(){
			$('.navi_sub').stop().slideDown();
			$('#navi_bg').stop().slideDown();

		}).mouseleave(function(){
			$('.navi_sub').stop().slideUp();
			$('#navi_bg').stop().slideUp();

		});

		$('.lnb>li').mouseover(function(){
			$(this).find('a').addClass('select')
		}) .mouseout(function(){
			$(this).find('a').removeClass('select')
		});
		});

		</script>
		<script type="text/javascript">
	$(document).ready(function(){
		var v=$('#b_m ul li');
		var vi=v.find('a img');
		var bt=$('#btn li');
		var cnt=0;
		var setIntervalId;
		var dir='next';
		
		st();

		$(window).resize(function(){
			st();
		});
		function st(){
		$('#b_m').css('height',vi.height()); //→ css에서 #main 값 지정안했을 경우 스크립트로 높이 지정하는 방법. 한 번에 높이 값 지정가능
			}

		bt.click(function(){
			var tg=$(this);
			var i=tg.index();
			bt.removeClass('on');
			tg.addClass('on');
			move(i);
		});

		function move(i){
			if(cnt==i) return;
			var ci=v.eq(cnt);
			var n=v.eq(i);
			if(dir=='next'){
				ci.css({left:0}).stop().animate({left:'-100%'});
				n.css({left:'100%'}).stop().animate({left:0});
			}else{
				ci.css({left:0}).stop().animate({left:'100%'});
				n.css({left:'-100%'}).stop().animate({left:0});
			}
			cnt=i;
		}
		$('#next').click(function(){
			dir='next';
			var a=cnt+1;
			if(a==v.length){
				a=0;
			}
			bt.eq(a).trigger('click');
		 });

		$('#prev').click(function(){
			dir='prev';
			var a=cnt-1;
			if(a<0){
				a=v.length-1;
			}
			bt.eq(a).trigger('click');
		 });


		timer();
		function timer(){
			setIntervalId=setInterval(function(){
				var a=cnt+1;
				if(a==v.length){
					a=0;
				}
				bt.eq(a).trigger('click');
			},5000);
		}

		});

		</script>

	<style type="text/css">
	*{margin: 0; padding: 0;}
	body{font:normal 12px '나눔바른고딕';}
	ul{list-style: none; /*overflow: hidden;*/}
	li{float:left;}
	a { text-decoration:none }
	
	#wrap #topmenu{width:100%; height: 180px; margin: 0 auto; border-bottom: 1px solid #eee;}
	#wrap #topmenu #top_in{ margin:0 auto; position:relative; height: 180px; width:100%;  background-color:rgba(255,255,255,0.8);}
	#wrap #topmenu h1{width:140px; margin:0 auto; position: absolute; z-index:12; left: 22%; top:15px;}
	
	#wrap #topmenu .lnb{position: absolute; top:120px; left:38%;}
	#wrap #topmenu .lnb>li:nth-child(4){margin-right: 120px;}
	#wrap #topmenu a{text-decoration: none;}
    #wrap #topmenu .lnb>li{color: #282828; font:normal 16px '나눔바른고딕'; margin:0 0px; float: left; position: relative; z-index:20; width: 150px; text-align: center;}
	
	#topmenu .lnb>li>a{color:#282828; display: block;}
	#topmenu .lnb>li>a:hover{color: #282828;} 
	#topmenu .lnb>li>a.select{color: #282828;}

	#topmenu .navi_sub{font-size: 13px; position: absolute; left:0; top:45px; width: 100%; text-align: center; }
	#topmenu .navi_sub>li{float: none;}
	#topmenu .navi_sub li a{color: #000; display: block; padding:8px 0; font-weight: normal; font-family: '맑은고딕'}
	#topmenu .navi_sub li a:hover{background-color:#e19f87; color: #fff;}
	#navi_bg{width: 100%; position: absolute; left:0; top:180px; height: 120px; background-color: rgba(255,255,255,0.8); z-index: 10;}



	#wrap #topmenu .gnb{position: absolute; right: 300px; top:15px;}
	#wrap #topmenu .gnb>li>a{color:#282828; }
	#wrap #topmenu .gnb li{margin-left: 15px; line-height: 22px;}
	#wrap #topmenu .gnb li{background-color: #fff;padding: 4px 5px 5px 5px; line-height: 15px; border-radius: 5px;}
	#wrap #topmenu .gnb .black{color:#000;}
	#wrap #topmenu .gnb .white{color:#fff;}
	#writeform{padding-top:50px; width: 1000px; height: auto; margin:0 auto;}
	#writeform	#summernote{width: 1000px; height: 500px; margin:0 auto;}
	
	#writeform	#summernote .note-editor note-frame panel panel-default{width: 1000px; height: 500px;}
	#writeform	#summernote .note-editing-area{height:500px;}
	
	#footer{background-color: #3c3c3c; height: 130px; position: relative; align:center;}
	#footer_in{ width:1340px; margin: 0 auto; text-align:center; padding-top:50px; }
	#footer #footer_in span{text-align:center; color:white;}
	
	</style>
</head>
<body>
	<div id="wrap"> <!--**** #wrap start ****-->
	
		<div id="topmenu"><!--**** #topmenu start ****-->
			<div id="top_in">
				<h1><a href="../"><img src="../images_sabujak/logo_top.png" alt="사부작 로고" /></a></h1>
				<ul class="lnb">
					<li><a href="#">브랜드소개</a>
						<ul class="navi_sub">
						<li><a href="#">Sabujak 이야기</a></li>
			   	   </ul></li>

					<li><a href="board.do?m=list">하비큐레이터</a>
						<ul class="navi_sub">
			   	     <li><a href="#">형진</a></li>
			   	     <li><a href="#">종현</a></li>
			   	     <li><a href="#">나영</a></li>
			   	  </ul>
					</li>
					<li><a href="../Sabujak/sabujak.do?m=list">커뮤니티</a>
						<ul class="navi_sub">
			   	   </ul>
					</li>
					<li><a href="../notice/board.do?m=list">공지사항</a>
						 <ul class="navi_sub">
			   	   </ul>
					</li>
				</ul>
				<ul class="gnb">
				<c:if test="${!empty sessionScope.loginUser}">
					<li><span  class="welcome">어서오세요 <font color = "#282828"><b> ${loginUser.m_name}</b></font>님</span></li>
					<li><a href="login/login.do?m=out">로그아웃</a></li>
					</c:if>
				<c:if test="${empty sessionScope.loginUser}">
					<li><a href="login/login.do?m=form">로그인</a></li>
					<li><a href="../Sabujak/sabujak.do?m=addmem">회원가입</a></li>
					</c:if>
				</ul>
			</div>
			<div id="navi_bg"></div>
		</div><!--**** #topmenu end ****-->
		<div id="writeform" style="width: 60%; margin: auto;">
			<form name= "input" method="post" action="board.do?m=updateOk&h_no=${hboard.h_no}" enctype="multipart/form-data">
		 		 <br/>
		  	  <input type="hidden" name="m">
			  <select name="category" onchange="setValue(this.value);">	  
			  <c:choose>
				  <c:when test="${hboard.hcode_fk== 1}">
				  	   <option value="0">글 분류 선택</option>
				       <option value="1" selected>영화리뷰</option>
				       <option value="2">자동차DIY</option>
				       <option value="3">가든DIY</option>
				  </c:when>
				  <c:when test="${hboard.hcode_fk== 2}">
				  	   <option value="0">글 분류 선택</option>
				       <option value="1">영화리뷰</option>
				       <option value="2" selected>자동차DIY</option>
				       <option value="3">가든DIY</option>
				  </c:when>
				  <c:when test="${hboard.hcode_fk== 3}">
				  	   <option value="0">글 분류 선택</option>
				       <option value="1">영화리뷰</option>
				       <option value="2" >자동차DIY</option>
				       <option value="3" selected>가든DIY</option>
				  </c:when>
			  </c:choose>
			  </select>
				<input type="text" name="writer" style="width: 27.2%;" value="${hboard.email_fk}" readonly/>
				<input id="subBtn" type="submit" value="수정하기"/>
				<br>
				<input type="text" name="title" style="width: 40%;" placeholder="제목" value="${hboard.h_sub}"/>
				<br /><br /> 
				<textarea id="summernote" name="content">${hboard.h_cont}</textarea>
				<span>썸네일 선택 :</span> <input type='text' name='Ofname' size='60'  value='${hboard.fname}' readonly/>
				<br/ > <input type="file" name="fname" accept=".gif, .jpg, .png">
				<br /><br /> 
			</form>
		</div>
		<div id="footer"><!--**** #footer start ****-->
			 <div id="footer_in">
					<span class="copyright">사업자등록번호 214-85-249-28 서울특별시 마포구 백범로 23 구프라자 3층 SABUJAK <br/> COPYRIGHT© 2019  SABUJAK ALL RIGHTS RESERVED.</span>
            </div> 
			</div><!--**** #footer end ****-->
	</div><!--**** #wrap end ****-->
	<script>
		function setValue(val){
			document.input.m.value=val;
			//location.href="insert.do?val="+val;

			
		}
	</script>
</body>
</html>