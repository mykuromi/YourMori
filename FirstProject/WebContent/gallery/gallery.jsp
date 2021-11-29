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
<title>gallery/gallery.jsp</title>
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
<h1>Gallery</h1>
<hr>
<table id="notice">
	<tr>
		<c:forEach var="bdto" items="${noticeList}">
			<td>
				<a href="${pageContext.request.contextPath}/NoticeContent.bo?num=${bdto.num}&pageNum=${pageNum}">
					<img src="${pageContext.request.contextPath}/upload/${bdto.file}" width="100px" height="100px"><br>
				</a>
				${bdto.num}<br>
				${bdto.subject}<br>
				${bdto.date}
			</td>
			<c:if test="${(bdto.num + 1)%4==0}">
				</tr><tr>
			</c:if>
		</c:forEach>
	</tr>
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
		<a href="./Gallery.bo?pageNum=${startPage-pageBlock}">[이전]</a>	
	</c:if>
	<c:forEach begin="${startPage}" end="${endPage}" step="1" var="i">
		<a href="./Gallery.bo?pageNum=${i}"> ${i} </a>
	</c:forEach>
	<c:if test="${endPage < pageCount}">
		<a href="./Gallery.bo?pageNum=${startPage+pageBlock}">[다음]</a>
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