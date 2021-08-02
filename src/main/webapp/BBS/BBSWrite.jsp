<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성하기</title>
</head>
<body>
    <h1> 게시물 작성하기</h1>
<div>
    <form method="post">

        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" ></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content"></textarea></td>
            </tr>
        </table>

        <input type="submit" formaction="BBSWrite.do" value="등록">
        <input type="submit" formaction="BBSList.do" value="목록">
    </form>
</div>

</body>
</html>