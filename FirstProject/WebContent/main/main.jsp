<%@page import="com.firstproject.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.firstproject.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main/main.jsp</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/front.css" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->

<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]--> 


</head>
<body>
<div id="wrap">
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>
<div id="main_img"><img src="${pageContext.request.contextPath}/images/main_img3.png"
 width="971" height="282"></div>
<!-- 메인이미지 들어가는곳 -->
<!-- 메인 콘텐츠 들어가는 곳 -->
<article id="front">
<!-- 
<div id="solution">
<div id="hosting">
<h3>subject1</h3>
<p>contents1</p>
</div>
<div id="security">
<h3>subject2</h3>
<p>contents2</p>
</div>
<div id="payment">
<h3>subject3</h3>
<p>contents3</p>
</div>
</div>
-->
 
<div class="clear"></div>
<div id="sec_news">
<h3><span class="orange">Yourmori</span> News</h3>
<dl>
<dt>유어모리 사이트 오픈!</dt>
<dd>모리걸분들만의 패션을 공유해주세요. ♥</dd>
</dl>
<dl>
<dt>유어모리 신상품 입고</dt>
<dd>자세한 내용은 GALLERY 게시판을 참고해주세요. :)</dd>
</dl>
</div>
<div id="news_notice">
<h3 class="brown">Yourmori Board</h3>
<table>
<%
	BoardDAO bdao = new BoardDAO();
	int pageSize = 5;
	int startRow = 1;
	List<BoardDTO> noticeList = bdao.getNoticeList(startRow, pageSize);
	for(int i=0; i<noticeList.size(); i++) {
		BoardDTO bdto = noticeList.get(i);
%>
<tr>
	<td class="contxt">
		<a href="${pageContext.request.contextPath}/NoticeContent.bo?num=<%=bdto.getNum()%>&pageNum=1">
<%
		if(bdto.getRe_lev()>0){
		int wid=bdto.getRe_lev()*10;
%>
		<img src="${pageContext.request.contextPath}/images/center/level.gif" width="<%=wid%>">
		<img src="${pageContext.request.contextPath}/images/center/re.gif">
<%
	}
%>
		<%=bdto.getSubject() %></a>
	</td>
	<td><%=bdto.getDate() %></td>
</tr>	
<%
	}
%> 
</table>
</div>
</article>
<!-- 메인 콘텐츠 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>