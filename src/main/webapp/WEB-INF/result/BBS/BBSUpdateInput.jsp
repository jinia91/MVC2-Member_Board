<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정하기</title>
</head>
<body>
    <h1> 게시물 수정하기</h1>
    
    	<c:if test="${sessionID!=null}">    
			<!-- 로그인 세션 있을때 -->
            ${sessionID} 님이 로그인중입니다.<br>
	</c:if>	
    
<div>
    <form method="post">

        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="${BBS.bbsTitle}" ></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content">${BBS.bbsContent}</textarea></td>
            </tr>
        </table>

        <input type="submit" formaction="BBSUpdateResult.do?bbsId=${BBS.bbsId}" value="수정">
        <input type="submit" formaction="BBSList.do" value="목록">
    </form>
</div>
</body>
</html>