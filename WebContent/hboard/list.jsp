<%@ page language="java" contentType="text/html; charset=utf-8" import="file.model.FileSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
 <head>
	<meta charset="UTF-8">
<title>베스트 100 - Sabujak curator</title>
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
	a{text-decoration:none;}
	#wrap{height:auto; position:relative;}
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

	#title_img {padding-top:25px; margin:0 auto;}
	
	#content{height:auto; position:relative; }
	#produck_sort{margin:0 auto; width:1200px; height: 60px; border-bottom: 1px solid #eee; padding-bottom:10px; }
	#produck_sort .sort a{text-decoration: none; color:#282828;}
	
	
	#produck_sort .sort>li{padding-left:10px; text-align: right; padding-top:40px;}
	#produck_sort .last-line{background-color: #eee; padding:5px; border-radius: 5px; color:#fff;}
	#produck_sort .totalcount{color:#ababab;}
	
	#curator_intro{padding-top:25px; align:center; margin:0 auto; position:relative; height:auto; width:70%;}
	#curator_intro dl{ float: left; padding: 15px; display:block;}
	#curator_intro dt img{width:400px;}
	#curator_intro dd{font-weight:bold; margin-top:20px; margin-bottom:20px; font-size:20px; color:#282828;}
	
	
	#pageSet{font:normal 14px '나눔바른고딕';  background-color:#eee; width:100px; height:20px; }
	#pageSet a{ text-decoration:none; color:#282828; background-color:#eee; border-radius: 5px;}
	
	#footer{background-color: #3c3c3c; height: 130px; position: relative; align:center; margin-top:50px;}
	.footer_in{width:1340px; margin: 0 auto; text-align:center; padding-top:50px; }
	.footer_in span{text-align:center; color:white;}

   
	
	</style>
</head>

			
<body>
<div id = "wrap" align ="center">
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

	<!--타이틀 이미지-->

	<div id="title_img">
	<h2>
		<img src="//hobbyinthebox.co.kr/web/upload/category/shop1_64_top_926270.jpg"  usemap="#categoryhead_top_image_map_name" alt="" />
	</h2>
	</div>
	<!--//종료-->
      
<!--상품소트-->
	<div id="content">
		<div id="produck_sort">
			<ul class="sort">
				<li><span class="totalcount">총 게시물 갯수: ${listResult.totalCount}</span>&nbsp;&nbsp;&nbsp;</li>
				<li><a href="board.do?m=list" class="xans-record-"><span>최신순</span></a></li>
				<li><a href="board.do?m=goodView" class="xans-record-"><span>조회순</span></a></li>
				<c:if test="${loginUser.m_kind==2}">
					<li><a href="board.do?m=write" class="last-line"><span>글쓰기</span></a></li>
				</c:if>
			</ul>
		</div>
	
	<!--상품목록-->
		<div id = "curator_intro">
			<c:if test="${empty listResult.list}">
				<dl>
					<dt>
						<a href="content.jsp">
							<img src="http://www.choistechnics.com/img/no_image.png" alt="데이터가 없습니다"/>
						</a>
					</dt>
					<dd>서버측 문제로 데이터를 가져오지 못함  or 데이터 없음</dd>
				</dl>
			</c:if>
			<c:forEach items="${listResult.list}" var="list">
				<dl>
					<dt>
					
						<a href="board.do?m=content&h_no=${list.h_no}&h_view=${list.h_view}">
						<c:if test="${empty list.fname}">
							<img src="http://www.choistechnics.com/img/no_image.png" alt="데이터가 없습니다"/>
						</c:if>
						<c:if test="${!empty list.fname}">
							<img src='../img_thumb/${list.fname}' alt="${list.fname}"/>
						</c:if>
						</a>
					</dt>
					<dd>${list.h_sub}</dd>
				</dl>
				</c:forEach>
				 <p style="clear:both;"></p>
		</div>
		<div id ="pageSet">
		 <c:forEach begin="1" end="${listResult.totalPageCount}" var ="i">
			 <a href="board.do?m=list&cp=${i}">
	            <c:choose>
	                <c:when test="${i==listResult.page}">
	    				<strong>${i}</strong>
	                </c:when>
		    		<c:otherwise>${i}</c:otherwise>
	    		</c:choose>   
			    </a>
		 </c:forEach>
		</div>
	</div>
	
	<div id="footer"><!--**** #footer start ****-->
		<div class="footer_in">
	 	  <span class="copyright">사업자등록번호 214-85-249-28 서울특별시 마포구 백범로 23 구프라자 3층 SABUJAK <br/> COPYRIGHT© 2019  SABUJAK ALL RIGHTS RESERVED.</span>
	     </div> 
	</div><!--**** #footer end ****-->

</div>
</body>
</html>
