<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:if test="${empty sessionScope.sessionId }">
	<c:redirect url="login"/>	
</c:if> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 쓰기</title>
</head>
<body>
	
	<h2>게시판 글 쓰기</h2>
	<hr>
	<form action="bWriteOk">
		글제목 : <input type="text" name="btitle"> <br><br>
		글내용 : <textarea cols="45" rows="10" name="bcontent"></textarea><br><br>
		작성자 : <input type="text" name="bwriter" value="${sessionScope.sessionId }" readonly="readonly"><br><br>
		<input type="submit" value="작성완료">
		<input type="reset" value="취소" onclick="window.location.href='boardList'">
	</form>

	
</body>
</html>