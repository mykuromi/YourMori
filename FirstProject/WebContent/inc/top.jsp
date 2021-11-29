<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>

<%
	// 로그인 제어
	String id = (String) session.getAttribute("id");
%>
<c:if test="${id == null}">
	<div id="login">
		<a href="${pageContext.request.contextPath}/MemberLogin.me">LOGIN</a>
		 | <a href="${pageContext.request.contextPath}/MemberJoin.me">JOIN</a>
	 </div>
</c:if>
<c:if test="${id != null}">
	<div id="login">
		${id }님 안녕하세요! =) | <a href="${pageContext.request.contextPath}/MemberUpdate.me">UPDATE</a>
		| <a href="${pageContext.request.contextPath}/MemberLogoutAction.me">LOGOUT</a> 
	</div>
</c:if>
<c:if test='${id.equals("admin")}'>
	<div id="login">
		+ <a href="${pageContext.request.contextPath}/MemberList.me">ADMIN MENU</a> 
	</div>
</c:if>	
<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="${pageContext.request.contextPath}/images/logo.png" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="#">♥</a></li>
	<li><a href="${pageContext.request.contextPath}/Main.me">HOME</a></li>
	<li><a href="${pageContext.request.contextPath}/AboutUs.me">ABOUT US</a></li>
	<li><a href="${pageContext.request.contextPath}/Notice.bo">NOTICE</a></li>
	<li><a href="${pageContext.request.contextPath}/Gallery.bo">GALLERY</a></li>
	<li><a href="${pageContext.request.contextPath}/Board.bo">BOARD</a></li>
	<li><a href="#">♥</a></li>
</ul>
</nav>
</header>