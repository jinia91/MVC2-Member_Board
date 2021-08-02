<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<h1>테스트 게시판</h1>
	<c:if test="${sessionID!=null}">    
			<!-- 로그인 세션 있을때 -->
            ${sessionID} 님이 로그인중입니다.<br>
	</c:if>	

    <div class="container">


        <table border="1">

        <thead>
            <tr>    
                <th>번호</th>
                <th width="200px">제 목</th>
                <th>작성날짜</th>
                <th>작성자</th>
                <th>조회수</th>
            </tr>
        </thead>


        

		<c:forEach var ="BBS" items="${BBSList}">
        <tbody>
            <tr>
                <td>${BBS.bbsId}</td>
                <td>${BBS.bbsTitle}</td>
                <td><fmt:formatDate value="${BBS.bbsDate}"/></td>
                <td>${BBS.id}</td>
                <td>${BBS.bbsHit}</td>
            </tr>
        </tbody>
        </c:forEach>

        </table>
    
    <a href="./BBSWrite.jsp	">
        <button>글쓰기</button>
	</a><br>
	
	<a href="../index.jsp"> 
	<button>Home</button> </a>



    </div>
    

</body>
</html>