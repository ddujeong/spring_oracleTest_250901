<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>외부 파일 불러오기</title>
<link rel="stylesheet" href="resources/css/test.css">
</head>
<body>
	<h2><span class="test" >이미지 불러오기</span></h2>
	<hr>
	<img alt="" src="${pageContext.request.contextPath }/resources/img/orange.jpg" width="300px" >
	<!-- 외부 파일은 resources 에서만 불러올 수 있음 -->
</body>
</html>