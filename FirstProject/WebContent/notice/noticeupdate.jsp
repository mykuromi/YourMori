<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice/noticewrite.jsp</title>
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
	request.setCharacterEncoding("UTF-8");		
	String id = (String) session.getAttribute("id");
	String pageNum = request.getParameter("pageNum");
%>
<jsp:useBean id="bdto" class="com.firstproject.board.db.BoardDTO" scope="request"/>
<article>
<h1>Update</h1>
<form action="${pageContext.request.contextPath}/NoticeUpdateProAction.bo?pageNum=<%=pageNum%>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${bdto.num}">
	<table id="notice">
		<tr>
			<td>Writer</td>
			<td><input type="text" name="name" value="${bdto.name}" size="70" readonly> </td>
		</tr>
		<tr>
			<td>Subject</td>
			<td><input type="text" name="subject" value="${bdto.subject}" size="70"></td>
		</tr>
		<tr>
			<td>File Upload</td>
			<td>
				<input type="file" name="file">| 현재 업로드된 파일 : ${bdto.file}
				<c:if test="${bdto.getFile() == null}">없음</c:if>
				<input type="hidden" name="oldfile" value="${bdto.file}">
			</td>
		</tr>	
		<tr>
			<td>Content</td>
			<td><textarea rows="10" cols="18" name="content" style="width:85%; height:100%;">${bdto.content}</textarea></td>
		</tr>	
	</table>
	<div id="table_search">
		<input type="submit" value="Update" class="btn">	
	</div>
</form>		
<div class="clear"></div>
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