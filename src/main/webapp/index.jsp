<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>MVC2 회원가입 게시판</title>
</head>

<body>

	<UL>
	

		<c:if test="${sessionID==null}">
			<!-- 로그인 세션 없을때 -->
			<a href="./member/MemberLogin.jsp">
				<LI>로그인하러가기</LI>
			</a>
			<a href="./member/MemberJoinInput.jsp">
				<LI>회원가입</LI>
			</a>
			<a href="./member/MemberSearch.jsp">
				<LI>회원정보찾기</LI>
			</a>
		</c:if>

			<c:if test="${sessionID!=null}">    
			<!-- 로그인 세션 있을때 -->
            ${sessionID} 님이 로그인중입니다.<br>
			<a href="./member/MemberLogout.do">
				<LI>로그아웃</LI>
			</a>
			<a href="./member/MemberUpdate.jsp">
				<LI>회원정보 수정</LI>
			</a>
			<a href="./member/MemberDelete.jsp">
				<LI>회원탈퇴</LI>
			</a>
			<a href="./BBS/BBSList.do">
			<li>게시판 보기</li>
			</a>
			
		</c:if>
		
		
	</UL>



</body>

</html>