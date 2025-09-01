<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 확인</title>
</head>
<body>
	<h2>글 내용 확인</h2>
	<hr>
	조회수 : ${bDto.bhit } <br><br>
	작성일 : ${bDto.bdate }<br><br>
	제목 :  ${bDto.btitle } <br><br>
	내용 : ${bDto.bcontent }<br><br>
	작성자 : ${bDto.bwriter }<br><br>
	
	<input type="button" value="목록으로 " onclick="window.location.href='boardList'">
</body>
</html>