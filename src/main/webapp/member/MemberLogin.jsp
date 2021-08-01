<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
  <meta charset="UTF-8">
 
  <title>회원가입</title>

  <style>
    #IDin {
      width: 123px;
      border: 2px solid rgba(48, 16, 16, 0.521);
      border-radius: 5px;
    }

    #PWDin {
      width: 123px;
      border: 2px solid rgba(48, 16, 16, 0.521);
      border-radius: 5px;
    }

    #IDin:focus {
      background-color: rgba(100, 198, 237, 0.212);
    }

    #PWDin:focus {
      background-color: rgba(100, 198, 237, 0.212);
    }

    input[type=submit] {
      background-color: rgba(237, 100, 134, 0.212);
    }


    input[type=submit]:hover {
      background-color: rgba(100, 198, 237, 0.212);
    }

  </style>



</head>

<body>
<c:if test="${sessionID==null}">

  <form id="formbox">

    <div> <input id="IDin" type="text" name="id" placeholder="아이디" autocomplete = "off"></div>

    <div> <input id="PWDin" type="password" name="pwd" placeholder="비밀번호"> </div>
	
	<div>
         <button type="submit" formaction="MemberLogin.do"
         formmethod="post">로그인</button>
         <button type="submit" formaction="MemberJoin.do"
         formmethod="post">회원가입</button>
    </div>     

  </form>
</c:if>
<c:if test="${sessionID!=null}">로그인상태입니다.<br>
	<a href="../index.jsp"> Home </a><br>
</c:if>
<c:if test="${loginResult==0}">비밀번호가 틀렸습니다.</c:if>
<c:if test="${loginResult==-1}">아이디가 없습니다. 회원가입해주세요</c:if>
${error}<br> 
</body>

</html>