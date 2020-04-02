<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Sabujak</title>
	<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
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
	#wrap{ height:auto;}
	#wrap #topmenu{width:100%; height: 600px; margin: 0 auto;background-image: url('images_sabujak/hobbyStoryback.jpg'); background-size:cover; background-repeat:no-repeat;}
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
	#wrap #topmenu .gnb .welcome{}
	#wrap #topmenu .gnb .black{color:#000;}
	#wrap #topmenu .gnb .white{color:#fff;}

/*배너고치기  배너배너배너배너배너배너 */
	
	#banner{ max-width: 100%;; margin: 0 auto; position: relative; overflow: hidden;}
	#b_m{position: relative; height: auto; /*overflow: hidden;*/ }
	#b_m ul{position: relative;}
	#b_m ul li{position: absolute;  width: 100%;}
	#b_m ul li a{display: block;}
	#b_m ul li a img{width: 100%;}
	#b_m ul #b_0{left:0;}
	#b_m ul #b_1{left:100%;}
	#b_m ul #b_2{left:200%;}

	#b_m p{position: absolute; top:50%; margin-top: -30px;}
	#prev{position: absolute; left:10%;}
	#next{position: absolute; right: 10%;}
/*	#banner .event{width:1900px; height: 552px; background-image: url(images/banner_event.jpg); margin:0 auto;}*/
/*                            */


	#middle{margin:0 auto; width: 1340px; height: 500px; position: relative;}
	#middle .hj{position: absolute; left:0; top:50px;}
	#middle .jh{position: absolute; left:478px; top:50px;}
	#middle .ny{position: absolute; right:0px; top:50px;}
	#middle dl{overflow: hidden; }
	#middle dl dd{position: absolute; top:40px; left:38%; text-align: center; font:normal 16px '나눔바른고딕'; margin:0 auto;}

	#middle b{color:#de6868; font-family:'나눔바른고딕'; font-size: 18px; }
	#middle span{font-size: 14px; color: #4c4c4c;}

	
	#footer{background-color: #3c3c3c; height: 130px; position: relative; align:center;}
	#footer_in{ width:1340px; margin: 0 auto; text-align:center; padding-top:50px; }
	#footer #footer_in span{text-align:center; color:white;}

	</style>
</head>
<body>
	<div id="wrap"> <!--**** #wrap start ****-->
		<div id="topmenu"><!--**** #topmenu start ****-->
			<div id="top_in">
				<h1><a href="#"><img src="images_sabujak/logo_top.png" alt="사부작 로고" /></a></h1>
				<ul class="lnb">
					<li><a href="#">브랜드소개</a>
						<ul class="navi_sub">
						<li><a href="#">Sabujak 이야기</a></li>
			   	   </ul></li>

					<li><a href="hboard/board.do?m=list">하비큐레이터</a>
						<ul class="navi_sub">
			   	     <li><a href="#">형진</a></li>
			   	     <li><a href="#">종현</a></li>
			   	     <li><a href="#">나영</a></li>
			   	  </ul>
					</li>
					<li><a href="Sabujak/sabujak.do?m=list">커뮤니티</a>
						<ul class="navi_sub">
			   	   </ul>
					</li>
					<li><a href="notice/board.do?m=list">공지사항</a>
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
					<li><a href="Sabujak/sabujak.do?m=addmem">회원가입</a></li>
					</c:if>
				</ul>
			</div>
			<div id="navi_bg"></div>
		</div><!--**** #topmenu end ****-->


<!-- 배너고치기	 -->	
			<div id="banner"><!--**** #banner start ****-->
			<p class="hobbyStory"></p>
			<div id="b_m">
			<ul>
				<li id="b_0"><a href="hboard/board.do?m=list"><img src="images_sabujak/banner1.png" alt="배너1"></a></li>
				<li id="b_1"><a href="Sabujak/sabujak.do?m=list"><img src="images_sabujak/banner2.png" alt="배너2"></a></li>
				<li id="b_2"><a href="notice/board.do?m=list"><img src="images_sabujak/banner3.png" alt="배너3"></a></li>
			</ul>
			<p id="prev"><img src="images/left2.png" alt="이전"></p>
			<p id="next"><img src="images/right2.png" alt="다음"></p>
		</div>
			<ul id="btn">
			<li class="on"><a href="#"></a></li>
			<li><a href="#"></a></li>
			<li><a href="#"></a></li>
			</ul>
		</div><!--**** #banner end ****-->



		<div id="middle"><!--**** #middle start ****-->
		 	<div id="middle_in">
			<dl class="hj">
				<dt><a href=""><img src="images_sabujak/hyungjin.png" alt="조형진" /></a></dt>
				<dd><b>형진씨</b><br /><br /><span>자동차 DIY 소개</span></dd>
			</dl>

			<dl class="jh">
				<dt><a href=""><img src="images_sabujak/jonghyun.png" alt="최종현" /></a></dt>
				<dd><b class="jh_b">종현오빠</b><br /><br /><span>가든 DIY 소개</span></dd>
			</dl>

			<dl class="ny">
				<dt><a href=""><img src="images_sabujak/nayoung.png" alt="한나영" /></a></dt>
				<dd><b>한나영</b><br /><br /><span>영화 리뷰 소개</span>
				</dd>
			</dl>
			</div> 
		</div><!--**** #middle end ****-->
		
		<div id="footer"><!--**** #footer start ****-->
			 <div id="footer_in">
					<span class="copyright">사업자등록번호 214-85-249-28 서울특별시 마포구 백범로 23 구프라자 3층 SABUJAK <br/> COPYRIGHT© 2019  SABUJAK ALL RIGHTS RESERVED.</span>
            </div> 
			</div><!--**** #footer end ****-->
	</div><!--**** #wrap end ****-->
</body>
</html>