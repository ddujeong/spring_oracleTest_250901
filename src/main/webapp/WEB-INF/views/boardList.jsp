<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>
	<h2>게시판 글 목록</h2>
	<hr>
	
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>이름</th>
			<th>조회수</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${bDtos }" var="bDto" varStatus="status">
			<tr>
				<td>${count - status.index }</td>
				<td><a href="contentView?bnum=${bDto.bnum }"> ${bDto.btitle }</a></td>
				<td>${bDto.bwriter }</td>
				<td>${bDto.memberDto.membername }</td>
				<td>${bDto.bhit }</td>
				<td>${bDto.bdate }</td>
				<td> <input type="button" value="삭제" onclick="window.location.href='boardDelete?bnum=${bDto.bnum}'" > </td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<input type="button" value="글쓰기" onclick="window.location.href='bWrite'" >
</body>
</html>