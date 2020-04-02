<%@page contentType="text/html;charset=utf-8"
	import="java.util.ArrayList, sabujak.domain.Sabujak"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Sabujak content</title>
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
		function showHide(){
			var writePlaceV = document.getElementById ("writePlace");
			if(writePlaceV.style.display =='none'){
				writePlaceV.style.display='block';
			}else{
				writePlaceV.style.display='none';
			}
		}
	</script>
<style type="text/css">
	*{margin: 0; padding: 0;}
	body{font:normal 12px '나눔바른고딕';}
	ul{list-style: none; /*overflow: hidden;*/}
	li{float:left;}
	a{text-decoration:none;}

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
	

	.main_content{margin-top:30px;}
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
	#main_content_p{min-height:200px;}
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

			   	  </ul>
					</li>
					<li><a href="sabujak.do?m=list">커뮤니티</a>
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
					<li><a href="sabujak.do?m=addmem">회원가입</a></li>
				</c:if>
				</ul>
			</div>
			<div id="navi_bg"></div>
		</div><!--**** #topmenu end ****-->
		<div id="cont_wrap">
		<div id="main_ul">
		<h1>커뮤니티</h1>
			<ul>
				<li><a href="sabujak.do?m=list">목록</a></li>
			<c:if test="${loginUser.email==sabujak.email_fk_pk}">
				<li>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="sabujak.do?m=upcont&c_no=${sabujak.c_no}">편집</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li><a href="sabujak.do?m=del&c_no=${sabujak.c_no}&fname=${sabujak.c_ofname}">삭제</a></li>
			</c:if>
			</ul>
			<ul class="view" >
				<li>조회수:${sabujak.c_view}</li>
			</ul>
		</div>
		
		  <div class="main_content" align="center">
		      <div id=cont_title>
		      	<ul class="title_ul">
		      		<li><h2>${sabujak.c_sub}</h2></li>
		      		<br><br>
		      		<li><span>no:</span> ${sabujak.c_no}&nbsp;&nbsp;</li>
		      		<li><span>writer:</span> ${sabujak.email_fk_pk}&nbsp;&nbsp;</li>
		      		<li><span>date:</span> ${sabujak.c_date}&nbsp;&nbsp;</li>
		      		<li><span>첨부파일:</span> <a href="sabujak.do?m=download&fname=${sabujak.c_fname}">${sabujak.c_ofname}</a></li>
		      	</ul>
		      </div>
		      <div id="main_content_p">
			      <p align="left">
			      	${sabujak.c_cont}
			      </p>
		      </div>
		<div id="main_review"> <!--**** #main_review start ****-->
		<div class="reviewWrite">
				<c:if test="${!empty sessionScope.loginUser}">
					<a class="review_write_open" href="javascript:showHide();"> 댓글남기기 ▼ </a>
					<div id="writePlace">
						<form name = "input" method="post"  action="sabujak.do?m=inreply&c_no=${sabujak.c_no}">
							<table>
								<tr>
									<td colspan="2"><textarea id="rev_text" name="content"  cols="60" rows="20"></textarea></td>
								</tr>
								<tr>
									<td width="200">
									 <input type="hidden" name="m"> 
									 <input type ="hidden" name="email" value="${loginUser.email}">
									 <input type ="hidden" name="h_view" value="${hboard.h_view}">
									 <input type="hidden" name="h_no" value="${hboard.h_no}">
									  </td>
									  <td class="reviewTB_TD2" colspan="2">
									  <input id="rev_btn" type="submit" value="댓글 달기"/>
										<a href="javascript:showHide();"> 댓글창닫기 </a>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</c:if>
			</div>
			
			 <h2>(커뮤니티 댓글)</h2>
         <table class="boardCommentWrap" >
           
            <colgroup>
               <col>
               <col width="300">
               <col>
            </colgroup>
            <tbody>
         <tr>
         <th>작성자</th>
         <th>내용</th>
         <th>작성일</th>
         <th></th>
         </tr>
           <c:if test="${empty reply.list}">
               <tr align="center">
                   <td colspan="5">댓글이 없습니다.</td>
               </tr>
              </c:if>      
         
         <c:forEach items="${reply.list}" var="reply">
         <tr>
         <th>${reply.email_fk}</th>
         <th>${reply.cre_cont}</th>
         <th>${reply.cre_date}</th>   
         <th>
         <c:if test="${reply.email_fk == loginUser.email}">
       	  <a href='sabujak.do?m=delreply&cre_no=${reply.cre_no}&c_no=${sabujak.c_no}'>삭제</a></th>
         </c:if>
         </tr>
         </c:forEach>
            </tbody>
         </table>
         
    (Total Page : ${reply.totalPageCount})
    &nbsp;&nbsp;
    <c:forEach begin="1" end="${reply.totalPageCount}" var="i"> 
        <a href="sabujak.do?m=content&c_no=${sabujak.c_no}&rcp=${i}">
            <c:choose>
                <c:when test="${i==reply.currentPage}">
                   <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    ${i}
                </c:otherwise>
            </c:choose>
       </a>&nbsp;
   </c:forEach>
    (Total Post : ${reply.totalCount} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
		</div><!--**** #main_review end ****-->
      </div><!--**** #cont_wrap end ****-->
      </div>
	<div id="footer"><!--**** #footer start ****-->
		<div class="footer_in">
	 	  <span class="copyright">사업자등록번호 214-85-249-28 서울특별시 마포구 백범로 23 구프라자 3층 SABUJAK <br/> COPYRIGHT© 2019  SABUJAK ALL RIGHTS RESERVED.</span>
	     </div> 
	     
		</div><!--**** #footer end ****-->
		
		</div>
</body>
</html>
