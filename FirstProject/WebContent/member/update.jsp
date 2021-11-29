<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/update.jsp</title>
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
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<jsp:include page="../inc/membersub.jsp"/>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<jsp:useBean id="mdto" class="com.firstproject.member.db.MemberDTO" scope="request"/>
<h1>Update</h1>
<form action="${pageContext.request.contextPath}/MemberUpdateAction.me" id="join">
<fieldset>
<legend>Basic Information</legend>
<label>ID</label>
<input type="text" name="id" class="id" value="${mdto.id}" readonly>
<input type="button" value="dup. check" class="dup"><br>
<label>Password</label>
<input type="password" name="pwd"><br>
<label>Name</label>
<input type="text" name="name" value="${mdto.name}"><br>
<label>E-Mail</label>
<input type="email" name="email" value="${mdto.email}"><br>
</fieldset>

<fieldset>
<legend>Optional Information</legend>
<label>Address</label>
<input type="text" name="address" id="address" size="50"  value="${mdto.address}" readonly/><br>
<label>Address Detail</label>
<input type="text" name="address_detail" id="address_detail" size="50"  value="${mdto.address_detail}"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile" value="${mdto.mobile}" ><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="Update" class="submit">
<input type="reset" value="Cancel" class="cancel">
<input type="button" value="회원 탈퇴" class="cancel" onclick="location.href='${pageContext.request.contextPath}/MemberDelete.me';">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("address").addEventListener("click", function(){ //주소입력칸을 클릭
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open({
            popupTitle: '우편번호 검색 팝업', //팝업창 타이틀 설정 
        	autoClose: true
        });
    });
}
</script>
</html>
</html>