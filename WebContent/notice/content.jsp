<%@page contentType="text/html;charset=utf-8"
	import="java.util.ArrayList, sabujak.domain.Sabujak"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="https://fonts.googleapis.com/css?family=Merriweather"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.navi_sub').hide();
		$('#navi_bg').hide();

		$('.lnb,#navi_bg').mouseenter(function() {
			$('.navi_sub').stop().slideDown();
			$('#navi_bg').stop().slideDown();

		}).mouseleave(function() {
			$('.navi_sub').stop().slideUp();
			$('#navi_bg').stop().slideUp();

		});

		$('.lnb>li').mouseover(function() {
			$(this).find('a').addClass('select')
		}).mouseout(function() {
			$(this).find('a').removeClass('select')
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var v = $('#b_m ul li');
		var vi = v.find('a img');
		var bt = $('#btn li');
		var cnt = 0;
		var setIntervalId;
		var dir = 'next';

		st();

		$(window).resize(function() {
			st();
		});
		function st() {
			$('#b_m').css('height', vi.height()); //→ css에서 #main 값 지정안했을 경우 스크립트로 높이 지정하는 방법. 한 번에 높이 값 지정가능
		}

		bt.click(function() {
			var tg = $(this);
			var i = tg.index();
			bt.removeClass('on');
			tg.addClass('on');
			move(i);
		});

		function move(i) {
			if (cnt == i)
				return;
			var ci = v.eq(cnt);
			var n = v.eq(i);
			if (dir == 'next') {
				ci.css({
					left : 0
				}).stop().animate({
					left : '-100%'
				});
				n.css({
					left : '100%'
				}).stop().animate({
					left : 0
				});
			} else {
				ci.css({
					left : 0
				}).stop().animate({
					left : '100%'
				});
				n.css({
					left : '-100%'
				}).stop().animate({
					left : 0
				});
			}
			cnt = i;
		}
		$('#next').click(function() {
			dir = 'next';
			var a = cnt + 1;
			if (a == v.length) {
				a = 0;
			}
			bt.eq(a).trigger('click');
		});

		$('#prev').click(function() {
			dir = 'prev';
			var a = cnt - 1;
			if (a < 0) {
				a = v.length - 1;
			}
			bt.eq(a).trigger('click');
		});

		timer();
		function timer() {
			setIntervalId = setInterval(function() {
				var a = cnt + 1;
				if (a == v.length) {
					a = 0;
				}
				bt.eq(a).trigger('click');
			}, 5000);
		}

	});
</script>
<script>
	$(document).ready(function() {

		$(".subCntcWrap").css("display", "none");

	});
</script>
<style type="text/css">
	*{margin: 0; padding: 0;}
	body{font:normal 12px '나눔바른고딕';}
	ul{list-style: none; /*overflow: hidden;*/}
	li{float:left;}

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

	#main_cont td{min-height:200px;}
	#cont_wrap{width:80%; margin:0 auto; position:relative; align:center;}
	#main_ul{width:80%; margin:0 auto; position:relative; height:80px; padding-top:60px; border-bottom:1px solid #eee;}
	#main_ul h1{padding-bottom:10px; text-align:center; font-size:30px;}
	#main_ul li>a{color:#282828; text-decoration: none;}
	#main_ul .view{align:right;}
	#main_ul .view>li{float:right;}
	
	#cont_title{ width: 80%; margin: 0 auto; background-color: #f8f8f8; height:80px; border-bottom:3px solid #ccc;}
	#cont_title .title_ul{align:center; padding-top:18px;}
	#cont_title .title_ul li{padding-left:10px;}
	#cont_title .title_ul>li:last-child{float:right; padding-right:20px;}
	#cont_title .title_ul li span{font-weight:bold;}
	#main_content_p{min-height:200px; border-bottom:1px soild #ccc;}
	#main_content_p p{padding-top:20px; width:80%; padding-left:20px; text-align:left;}
	
	#rev_text{height:100px;}
	#writePlace{width:80%; background-color:#eee; margin:10px auto;  display:block; margin-bottom:40px;}
	#writePlace table{padding-top:20px;}
	.reviewTB_TD2{text-align:right; padding-bottom:10px;}
	.reviewTB_TD2>a{text-decoration: none; color: white; background-color:gray; border-radius:2px; padding:3px; 
		align:center; font-size: 12px;}
	#is_review{width:80%; margin:30px auto; align:center;}
	 #is_review span{text-align:center; width:80%;}
	#not_review{width:80%; margin:0 auto; text-align:center; margin:100px auto;}
	#rev_btn{background-color:#066978; border:none; color:white; padding:1px 3px; border-radius:2px;}
	.reviewWrite{padding:30px;}
	#main_review .reviewWrite .review_write_open {text-decoration: none; border-radius:2px; padding:3px;
	align:center; color:#066978; font-size:14px; font-weight:bold;}
	.boardCommentWrap{margin: 20px auto; padding-top:20px; border-bottom:1px solid #ccc;}
	.boardCommentWrap h2{padding-top:20px;}
	.boardCommentWrap th{padding:5px;}
	#pageSet{text-align:center; margin-top: 10px;}
	#pageSet a{text-decoration:none; font-size:14px; color:#282828;}
	
	#footer{background-color: #3c3c3c; height: 130px; position: relative; align:center; margin-top:50px;}
	.footer_in{width:1340px; margin: 0 auto; text-align:center; padding-top:50px; }
	.footer_in span{text-align:center; color:white;}
	
	#footer{background-color: #3c3c3c; height: 130px; position: relative; align:center; margin-top:50px;}
	.footer_in{width:1340px; margin: 0 auto; text-align:center; padding-top:50px; }
	.footer_in span{text-align:center; color:white;}
</style>
</head>
<body>
<div id="wrap">
	<div id="topmenu"><!--**** #topmenu start ****-->
			<div id="top_in">
				<h1><a href="../"><img src="../images_sabujak/logo_top.png" alt="사부작 로고" /></a></h1>
				<ul class="lnb">
					<li><a href="#">브랜드소개</a>
						<ul class="navi_sub">
						<li><a href="#">Sabujak 이야기</a></li>
			   	   </ul></li>

					<li><a href="../hboard/board.do?m=list">하비큐레이터</a>
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
					<li><a href="board.do?m=list">공지사항</a>
						 <ul class="navi_sub">
			   	   </ul>
					</li>
				</ul>
				<ul class="gnb">
				<c:if test="${!empty sessionScope.loginUser}">
					<li><span  class="welcome">어서오세요 <font color = "#282828"><b> ${loginUser.m_name}</b></font>님</span></li>
					<li><a href="../login/login.do?m=out">로그아웃</a></li>
				</c:if>
				<c:if test="${empty sessionScope.loginUser}">
					<li><a href="../login/login.do?m=form">로그인</a></li>
					<li><a href="../Sabujak/sabujak.do?m=addmem">회원가입</a></li>
				</c:if>
				</ul>
			</div>
			<div id="navi_bg"></div>
		</div><!--**** #topmenu end ****-->
		<div id="cont_wrap">
		<div id="main_ul">
		<h1>공지사항</h1>
			<ul>
				<li><a href="board.do?m=list">목록</a></li>
			<c:if test="${loginUser.email==Notice.email_fk}">
				<li>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="board.do?m=update&n_no=${Notice.n_no}">편집</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li><a href="board.do?m=del&n_no=${Notice.n_no}&fname=${Notice.fname}">삭제</a></li>
			</c:if>
			</ul>
		</div>
		  <div class="main_content" align="center">
		      <div id=cont_title>
		      	<ul class="title_ul">
		      		<li><h2>${Notice.n_sub}</h2></li>
		      		<br><br>
		      		<li><span>no:</span> ${Notice.n_no}&nbsp;&nbsp;</li>
		      		<li><span>writer:</span> ${Notice.email_fk}&nbsp;&nbsp;</li>
		      		<li><span>date:</span> ${Notice.n_date}&nbsp;&nbsp;</li>
		      		<li><span>첨부파일:</span><a href="../Sabujak/sabujak.do?m=download&fname=${Notice.fname}">${Notice.ofname}</a></li>
		      	</ul>
		      </div>
				  <div id="main_content_p">
				      <p align="left">
				      	${Notice.n_cont}
				      </p>
		     	 </div>		
		     	 <hr width="80%" color="#ccc" size="1" noshade>
		</div><!--**** #main_review end ****-->
      </div><!--**** #cont_wrap end ****-->
	<div id="footer"><!--**** #footer start ****-->
		<div class="footer_in">
	 	  <span class="copyright">사업자등록번호 214-85-249-28 서울특별시 마포구 백범로 23 구프라자 3층 SABUJAK <br/> COPYRIGHT© 2019  SABUJAK ALL RIGHTS RESERVED.</span>
	     </div> 
		</div><!--**** #footer end ****-->
		</div>
</body>
</html>
