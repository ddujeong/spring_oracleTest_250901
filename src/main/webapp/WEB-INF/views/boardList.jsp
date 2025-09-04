<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 40px;
        background-color: #f9f9f9;
    }

    h2 {
        color: #333;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: #fff;
        margin-bottom: 20px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #f0f0f0;
        color: #555;
    }

    a {
        color: #007BFF;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    .btn {
        padding: 8px 15px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .btn:hover {
        background-color: #0056b3;
    }

    .pagination {
        text-align: center;
        margin-top: 20px;
    }

    .pagination a {
        display: inline-block;
        padding: 8px 12px;
        margin: 0 5px;
        border: 1px solid #ddd;
        color: #007BFF;
        text-decoration: none;
        border-radius: 4px;
    }

    .pagination a.active {
        background-color: #007BFF;
        color: white;
        font-weight: bold;
    }

    .pagination a:hover {
        background-color: #0056b3;
        color: white;
    }
</style>
</head>
<body>
    <h2>📋 게시판 글 목록</h2>
    <table>
        <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>작성자</th>
            <th>이름</th>
            <th>조회수</th>
            <th>작성일</th>
            <th>삭제</th>
        </tr>
        <c:forEach items="${bDtos}" var="bDto" varStatus="status">
            <tr>
                <td>${count - status.index}</td>
                <td><a href="contentView?bnum=${bDto.bnum}">${bDto.btitle}</a></td>
                <td>${bDto.bwriter}</td>
                <td>${bDto.memberDto.membername}</td>
                <td>${bDto.bhit}</td>
                <td><fmt:formatDate value="${bDto.bdate}" pattern="yyyy-MM-dd"/></td>
                <td><input type="button" class="btn" value="삭제" onclick="window.location.href='boardDelete?bnum=${bDto.bnum}'" ></td>
            </tr>
        </c:forEach>
    </table>

    <!-- 페이징 영역 -->
    <div class="pagination">
    <c:if test="${pageNum >1 }">
      <a href="boardList?pageNum=1">&laquo;</a>
      <a href="boardList?pageNum=${pageNum-1}">&lsaquo;</a>
      </c:if>
      <c:forEach var="i" begin="${startPage }" end="${endPage }">
      	<c:choose>
      		<c:when test="${i == pageNum }">
      			<a href="#" class="active">${i}</a>
      		</c:when>
      		<c:otherwise>
      			<a href="boardList?pageNum=${i}">${i}</a>
      		</c:otherwise>
      	</c:choose>
      	
      </c:forEach>
      <c:if test="${pageNum <totalPage }">
      <a href="boardList?pageNum=${pageNum+1}">&rsaquo;</a>
      <a href="boardList?pageNum=${totalPage }">&raquo;</a>
      </c:if>
    </div>

    <!-- 글쓰기 버튼 -->
    <div style="text-align: right;">
        <input type="button" class="btn" value="글쓰기" onclick="window.location.href='bWrite'" >
    </div>
</body>
</html>
