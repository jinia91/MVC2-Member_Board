<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>

<h1>게시글 상세보기</h1><br>

	<c:if test="${sessionID!=null}">    
			<!-- 로그인 세션 있을때 -->
            ${sessionID} 님이 로그인중입니다.<br>
	</c:if>	


<body>

	<table border ="1" width = "100%">
	
		<tr>
			<th colspan = "6">제목</th>
		</tr>
		<tr>
			<td colspan = "6">${BBS.bbsTitle}</td>
		</tr>
	
	
		<tr>
			<th>작성자</th>
			<td>${BBS.id}</td>
			<th>작성일</th>
			<td><fmt:formatDate value="${BBS.bbsDate}"/>
			<th>조회수</th>
			<td>${BBS.bbsHit}</td>
		</tr>
				
		<tr>
			<th colspan = "6">내용</th>
		</tr>
		<tr>
			<td colspan = "6">${BBS.bbsContent}</td>		
		</tr>
		
		
	</table>
	
	<a href="BBSList.do"><button>목록</button></a>	
	<a href="BBSUpdate.do?bbsId=${BBS.bbsId}&id=${BBS.id}"><button>수정</button></a>
	<a href="BBSDelete.do?bbsId=${BBS.bbsId}&id=${BBS.id}"><button>삭제</button></a> 	
	<a href="./BBSWrite.jsp"><button>글쓰기</button></a>
   
</body>
</html>