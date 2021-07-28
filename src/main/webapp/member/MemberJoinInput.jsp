<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>

<h1>회원가입</h1>

${error}<br>

<form action="MemberJoin.do" method="post">

    ID <br>
    <input type="text" name="id" required oninvalid="alert('ID를 입력해주세요!');" maxlength="10"><br>
    비밀번호 <br>
    <input type="password" name="pwd" required oninvalid="alert('비밀번호를 입력해주세요!');" maxlength="20"><br>
    E-Mail <br>
    <input type="email" name="email" required oninvalid="alert('E-Mail을 입력해주세요!');" maxlength="30"> <br>
    생년월일 <br>
    <input type="date" name="birth" required oninvalid="alert('생년월일을 입력해주세요!');"><br>
    성별 <br>	
    <input type="radio" name="gender" value="male" checked> 남자
    <input type="radio" name="gender" value="female"> 여자<br>

    <input type="submit" value="가입하기">
    

</form>

</body>
</html>