<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.awt.Image"%>
<%@page import="com.sun.jimi.core.Jimi"%>
<%@page import="com.sun.jimi.core.JimiException"%>
<%@page import="com.sun.jimi.core.JimiUtils"%>
  <!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Sabujak</title>
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
		<script>
	</script>
	 <script language="javascript">
              function check(){
                if(f1.rev_cont.value == ""){
                 alert("리뷰를 작성해주세요.");
                 return;
             }
	           var sel = document.getElementById("rev_good");
	           var valCheck = sel.options[sel.selectedIndex].value;
	             if(valCheck==0){
	                  alert("평점을 선택해주세요.");
	                 return;
	             }
           	 function setValue(val){
        			document.input.m.value=val;
        			//location.href="insert.do?val="+val;
        			
        		}
            	  f1.submit();
            }
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
	#cont_wrap{width:80%; margin:0 auto; position:relative; align:center;}
	
	#main_ul{width:80%; margin:0 auto; position:relative; height:20px; padding-top:60px; border-bottom:1px solid #eee;}
	#main_ul li>a{color:#282828; text-decoration: none;}
	#main_ul .view{align:right;}
	#main_content{width: 80%; margin: 25px auto; position:relative; align:center;  min-height:200px;}
	#main_content .main_content{align:center;  }
	#thumbWrap{width:80%; margin:0 auto; border-top:1px solid #eee;}
	#thumbWrap .thumbfile{padding-top:10px;}
	#main_review{width:80%; margin:0 auto; align:center; border-top:1px solid #eee; margin-top: 20px;}
	.star{font-size:20px; font-family:"나눔스퀘어"; color:#ff5356;}
	.starS{font-size:15px; font-family:"나눔스퀘어"; color:#ff5356;}
	.reviewWrite{ margin-top:20px; text-align:center;}
	#main_review .reviewWrite .review_write_open {text-decoration: none; border-radius:2px; padding:3px; 
		align:center; color:#e19f87; font-size:14px; font-weight:bold;}
	table{margin: 0 auto; width:500px; height:100px; border-bottom:1px solid #ccc; }
	table tr td{ padding: 5px;}
	.view>li{float:right;}
	#rev_text{height:100px;}
	#writePlace{width:60%; height:60%; background-color:#eee; margin:10px auto;  display:block;}
	#writePlace table{padding-top:20px;}
	.reviewTB_TD2{text-align:right; }
	.reviewTB_TD2>a{text-decoration: none; color: white; background-color:gray; border-radius:2px; padding:3px; 
		align:center; font-size: 12px;}
	#is_review{width:80%; margin:30px auto; align:center;}
	 #is_review span{text-align:center; width:80%;}
	#not_review{width:80%; margin:0 auto; text-align:center; margin:100px auto;}
	#rev_good{width:100px;}
	#rev_btn{background-color:#e19f87; border:none; color:white; padding:1px 3px; border-radius:2px;}
	
	#pageSet{text-align:center; margin-top: 10px;}
	#pageSet a{text-decoration:none; font-size:14px; color:#282828;}
	#footer{background-color: #3c3c3c; height: 130px; position: relative; align:center; margin-top:50px;}
	.footer_in{width:1340px; margin: 0 auto; text-align:center; padding-top:50px; }
	.footer_in span{text-align:center; color:white;}
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
		<div id ="cont_wrap"><!--**** #cont_wrap start ****-->
		<div id="main_ul">
		<ul>
			<li><a href="board.do?m=list">목록</a></li>
		<c:if test="${loginUser.email==hboard.email_fk}">
			<li>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="board.do?m=update&h_no=${hboard.h_no}&h_view=${hboard.h_view}">편집</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
			<li><a href="board.do?m=del&h_no=${hboard.h_no}&fname=${hboard.fname}">삭제</a></li>
		</c:if>
		</ul>
		<ul class="view" >
			<li>조회수:${hboard.h_view}</li>
		</ul>
		</div>
		<div id="main_content">
		<br />
					${hboard.h_cont}
		</div>
		<div id="thumbWrap">
		<br/>
		<span class="thumbfile">썸네일 파일명:
			<a href='board.do?m=download&fname=${hboard.fname}'>${hboard.ofname}</a>
		</span> 
		</div>
		
		<div id="main_review"> <!--**** #main_review start ****-->
		<div class="reviewWrite">
				<c:if test="${!empty sessionScope.loginUser}">
					<a class="review_write_open" href="javascript:showHide();"> 리뷰남기기 ▼ </a>
					
					<div id="writePlace">
						<form name = "f1" method="post"  action="board.do?m=rev_save">
							<table>
								<tr>
									<td colspan="2"><textarea id="rev_text"  name="rev_cont" cols="60" rows="20"></textarea></td>
								</tr>
								<tr>
									<td width="200">
									 <input type="hidden" name="m"> 
									 <input type ="hidden" name="email_fk" value="${loginUser.email}">
									 <input type ="hidden" name="h_view" value="${hboard.h_view}">
									 <input type="hidden" name="h_no" value="${hboard.h_no}">
									<select id="rev_good" name="rev_good" onchange="setValue(this.value);">	   
									  	   <option value="" selected>평점 선택</option>
									       <option class="starS" value="1" >★☆☆☆☆</option>
									       <option class="starS" value="2">★★☆☆☆</option>
									       <option class="starS" value="3">★★★☆☆</option>
									       <option class="starS" value="4">★★★★☆</option>
									       <option class="starS" value="5">★★★★★</option>
									  </select>
									  </td>
									  <td class="reviewTB_TD2" colspan="2">
										<input type="button" value="등록" onclick="check()"id="rev_btn">
										<a href="javascript:showHide();"> 리뷰창닫기 </a>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</c:if>
		</div>
		<c:if test="${empty listResultRev.list}">
		<div id="not_review">
			<b>리뷰가 없습니다</b>
		</div>
			</c:if>
			<c:if test="${!empty listResultRev.list}">
			<div id="is_review">
			<c:set var = "total" value="0" />
			<c:forEach items = "${rev}" var = "rev">
				<c:set var = "total" value = "${total + rev.rev_good}" />
			</c:forEach>
			<c:if test="${listResultRev.totalCount >0}" >
			</c:if>
			<h2 align="center">리뷰  (<c:out value="${total/listResultRev.totalCount}" />/5.0)</h2>
			
			<c:forEach items="${listResultRev.list}" var="list">
			<table>
				<tr>
					<c:if test="${list.rev_good==1}">
						<td colspan=3><span class="star">★☆☆☆☆</span></td>
					</c:if>
					<c:if test="${list.rev_good==2}">
						<td colspan=3><span class="star">★★☆☆☆</span></td>
					</c:if>
					<c:if test="${list.rev_good==3}">
						<td colspan=3><span class="star">★★★☆☆</span></td>
					</c:if>
					<c:if test="${list.rev_good==4}">
						<td colspan=3><span class="star">★★★★☆</span></td>
					</c:if>
					<c:if test="${list.rev_good==5}">
						<td colspan=3><span class="star">★★★★★</span></td>
					</c:if>
					<c:if test="${loginUser.email==list.email_fk}">
						<td align="right">
							<a href="board.do?m=updateRev&h_no=${hboard.h_no}&rev_no=${list.rev_no}&h_view=${hboard.h_view}" style="text-decoration:none;">수정</a>
							<a href="board.do?m=rev_del&h_no=${hboard.h_no}&rev_no=${list.rev_no}&h_view=${hboard.h_view}" style="text-decoration:none;">삭제</a>
						</td>
					</c:if>
				</tr>
				<tr>
					<td colspan=2>${list.email_fk}</td>
					<td colspan=2 align="right">${list.rev_date}</td>
				</tr>
				<tr>
					<td colspan=4>${list.rev_cont}</td>
				</tr>
			</table>
			 </c:forEach>
			 <div id ="pageSet">
 			<c:forEach begin="1" end="${listResultRev.totalPageCount}" var ="i">
			 <a href="board.do?m=content&cpRev=${i}&h_no=${hboard.h_no}&h_view=${hboard.h_view}">
			            <c:choose>
			                <c:when test="${i==listResultRev.page}">
			    				<strong>${i}</strong>
			                </c:when>
				    		<c:otherwise>${i}</c:otherwise>
			    		</c:choose>   
			    	</a>
			 </c:forEach>
				</div>
			</div>
			</c:if>
		</div><!--**** #main_review end ****-->
		
	  </div><!--**** #cont_wrap end ****-->
	  	<div id="footer"><!--**** #footer start ****-->
		<div class="footer_in">
	 	  <span class="copyright">사업자등록번호 214-85-249-28 서울특별시 마포구 백범로 23 구프라자 3층 SABUJAK <br/> COPYRIGHT© 2019  SABUJAK ALL RIGHTS RESERVED.</span>
	     </div> 
		</div><!--**** #footer end ****-->
	 </div><!--**** #wrap end ****-->
  </body>
</html>