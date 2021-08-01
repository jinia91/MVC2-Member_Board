<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>



<c:if test="${member==null}">		
<form action = "MemberSearchAll.do" method="post">

<h3>비밀번호를 입력해주세요</h3>
<input type="password" name = "pwd" required
			oninvalid="alert('비밀번호를 입력해주세요!');" /><br>
<input type="submit" value="확인">
</form>
</c:if>


<c:if test="${member!=null}">

<h3>회원정보 수정하기</h3>

	<form action="MemberUpdate.do" method="post">

		ID(수정불가) <br> <input type="text" name="id" required
			oninvalid="alert('ID를 입력해주세요!');" maxlength="10" value="${member.id}" readonly><br>
		비밀번호 <br> <input type="password" name="pwd" required
			oninvalid="alert('비밀번호를 입력해주세요!');" maxlength="20" value="${member.pwd}"><br>
		E-Mail <br> <input type="email" name="email" required
			oninvalid="alert('E-Mail을 입력해주세요!');" maxlength="30" value="${member.email}"> <br>
		생년월일 <br> <input type="date" name="birth" required value="${member.birth}"
			><br>  
			<input type="submit" value="수정하기">
	</form>

</c:if>

</body>
</html>