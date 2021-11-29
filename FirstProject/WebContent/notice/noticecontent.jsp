<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice/noticecontent.jsp</title>
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
<script type="text/javascript">
	function deletecheck(){
		var deleteconfirm = confirm("해당 글을 삭제하시겠습니까?");
		if(deleteconfirm){
			location.href='${pageContext.request.contextPath}/NoticeDelete.bo?num=${bdto.num}&file=${bdto.file}';
		}
	}
</script>
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
	System.out.println("View : pageNum = "+pageNum);	
%>
<jsp:useBean id="bdto" class="com.firstproject.board.db.BoardDTO" scope="request"/>
<article>
<h1>Write</h1>
<form action="${pageContext.request.contextPath}/NoticeDelete.bo?num=${bdto.num}" method="post" enctype="multipart/form-data"
	onsubmit="return confirm('해당 글을 정말로 삭제하시겠습니까?');">
<table id="notice">
	<tr>
		<td>Article No#</td>
		<td>${bdto.num}</td>
		<td>Readcount</td>
		<td>${bdto.readcount}</td>
	</tr>
	<tr>
		<td>Writer</td>
		<td>${bdto.name}</td>
		<td>Date</td>
		<td>${bdto.date}</td>
	</tr>
	<tr>
		<td>Subject</td>
		<td colspan="3">${bdto.subject}</td>
	</tr>
	<tr>
		<td>File</td>
		<td colspan="3">
			<a href="${pageContext.request.contextPath}/upload/${bdto.file}" download>${bdto.file}</a>
			<c:if test="${bdto.getFile() == null}">no uploaded file</c:if>
		</td>
	</tr>	
	<tr>
		<td>Content</td>
		<td colspan="3">${bdto.content}</td>
	</tr>	
</table>
<div id="table_search">
<c:if test="${id != null}">
	<c:if test="${id.equals(bdto.getName())}">
		<input type="button" value="Update" class="btn" onclick="location.href='${pageContext.request.contextPath}/NoticeUpdate.bo?num=${bdto.num}&pageNum=<%=pageNum%>';">
		<input type="submit" value="Delete" class="btn">
		<input type="hidden" name="file" value="${bdto.file}" class="btn">
	</c:if>
	<input type="button" value="Reply" class="btn" onclick="location.href='${pageContext.request.contextPath}/NoticeReply.bo?num=${bdto.num}&pageNum=<%=pageNum%>&re_ref=${bdto.re_ref}&re_lev=${bdto.re_lev}&re_seq=${bdto.re_seq}';">
</c:if>
<input type="button" value="List" class="btn" onclick="location.href='${pageContext.request.contextPath}/Notice.bo?num=${bdto.num}&pageNum=<%=pageNum%>';">
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