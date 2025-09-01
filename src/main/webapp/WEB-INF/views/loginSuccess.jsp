<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<h2>로그인 성공</h2>
	<hr>
	<h3>${sessionScope.sessionId }님 반갑습니다.</h3>
	<a href="logout"> 로그 아웃</a><br>
	<a href="bWrite"> 게시판 글쓰기</a><br>
	<a href="bList"> 게시판 리스트</a><br>
</body>
</html>