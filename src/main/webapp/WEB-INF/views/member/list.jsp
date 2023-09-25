<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../default/header.jsp" %>
<h3>list 페이지</h3>
size : ${list.size() }
<table border="1">
	<tr>
		<th>id</th>
		<th>pw</th>
		<th>addr</th>
	</tr>
	<c:forEach var="dto" items="${list }">
	<tr>
		<th><a href="info?id=${dto.id }">${dto.id }</a></th>
		<th>${dto.pw }</th>
		<th>${dto.addr }</th>
	</tr>
	</c:forEach>
</table>

</body>
</html>