<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.firstproject.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice/notice.jsp</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/subpage.css" rel="stylesheet" type="text/css">
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
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<jsp:include page="../inc/noticesub.jsp"/>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<%
	String id = (String) session.getAttribute("id");
	List<BoardDTO> boardList = (List<BoardDTO>) request.getAttribute("noticeList");
	String pageNum = (String) request.getAttribute("pageNum");
	Integer cnt = (Integer) request.getAttribute("cnt");
	Integer pageCount = (Integer) request.getAttribute("pageCount");
	Integer pageBlock = (Integer) request.getAttribute("pageBlock");
	Integer startPage = (Integer) request.getAttribute("startPage");
	Integer endPage = (Integer) request.getAttribute("endPage");
%>
	
<article>
<h1>Notice</h1>
<table id="notice">
	<tr>
		<th class="tno">No.</th>
		<th class="ttitle">Title</th>
		<th class="twrite">Writer</th>
		<th class="tdate" style="width:15%;">Date</th>
		<th class="tread">Read</th>
	</tr>
	<c:forEach var="bdto" items="${noticeList}">
		<tr>
			<td>${bdto.num}</td>
			<td class="left" onclick="location.href='${pageContext.request.contextPath}/NoticeContent.bo?num=${bdto.num}&pageNum=${pageNum}';">
				<c:if test="${bdto.getRe_lev() > 0}">
					<img src="${pageContext.request.contextPath}/images/center/level.gif" alt="level.gif" width="${bdto.getRe_lev() * 10}">
					<img src="${pageContext.request.contextPath}/images/center/re.gif" alt="re.gif">
				</c:if>
				${bdto.subject}
			</td>
			<td>${bdto.name}</td>
			<td>${bdto.date}</td>
			<td>${bdto.readcount}</td>
		</tr>
	</c:forEach>
</table>
<c:if test="${id != null}">
	<div id="table_search">
		<input type="button" value="Write" class="btn" onclick="location.href='${pageContext.request.contextPath}/NoticeWrite.bo';">
	</div>	
</c:if>
<form action="./NoticeSearch.bo" name="search" id="table_search" style="margin:0; padding:0;">
	<div id="table_search">
		<input type="text" name="search" class="input_box">
		<input type="submit" value="Search" class="btn">
	</div>
</form>
<div class="clear"></div>
<div id="page_control">
	<c:if test="${startPage > pageBlock}">
		<a href="./Notice.bo?pageNum=${startPage-pageBlock}">[이전]</a>	
	</c:if>
	<c:forEach begin="${startPage}" end="${endPage}" step="1" var="i">
		<a href="./Notice.bo?pageNum=${i}"> ${i} </a>
	</c:forEach>
	<c:if test="${endPage < pageCount}">
		<a href="./Notice.bo?pageNum=${startPage+pageBlock}">[다음]</a>
	</c:if>	
</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>