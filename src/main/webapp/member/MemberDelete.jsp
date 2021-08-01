<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${error}

<form action = "MemberDelete.do" method="post">

<h3>정말로 삭제하시겠습니까?</h3>
삭제하시려면 비밀번호를 입력해주세요<br />
<input type="password" name = "pwd" required
			oninvalid="alert('비빌번호를 입력해주세요!');" /><br>
<input type="submit" value="확인">
</form>

</body>
</html>