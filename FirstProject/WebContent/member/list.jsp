<%@page import="com.firstproject.member.db.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/list.jsp</title>
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
<jsp:include page="../inc/membersub.jsp"/>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<h1>Members' Information</h1>
<jsp:useBean id="mdto" class="com.firstproject.member.db.MemberDTO"/>
<table id="notice">
	<tr>
	<th class="tno">IDX</th>
		<th class="twrite">ID</th>
		<th class="twrite">Name</th>
		<th class="twrite">Email</th>
		<th class="ttitle">Address</th>
		<th class="twrite">Mobile</th>
		<th class="tread" style="width:15%;">Date</th>
	</tr>
	<c:forEach var="mdto" items="${memberList}">
		<tr>
			<td>${mdto.idx} </td>
			<td>${mdto.id} </td>
			<td>${mdto.name} </td>
			<td>${mdto.email} </td>
			<td class="left">${mdto.address} ${mdto.address_detail} </td>
			<td>${mdto.mobile} </td>
			<td>${mdto.joindate} </td>
		</tr>
	</c:forEach>
</table>
<div id="table_search">
</div>
<div class="clear"></div>
<div id="page_control">
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