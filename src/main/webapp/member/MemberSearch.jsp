<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원정보 찾기</title>
</head>

<body>

	<h1>회원 정보 찾기</h1>


${error}
	<form action="MemberSearch.do" method="post">

		ID <br> 
		<input type="text" name="id" required
			oninvalid="alert('ID를 입력해주세요!');" maxlength="10"><br>

		가입에 사용한 E-Mail <br> 
		<input type="email" name="email" required
			oninvalid="alert('E-Mail을 입력해주세요!');" maxlength="30"> <br>

		<input type="submit" value="PWD 찾기">

	</form>

</body>

</html>