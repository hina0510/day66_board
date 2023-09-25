<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/daumPost.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
	<h1>CARE LAB</h1>
	<a href="${contextPath }/index">HOME</a>
	<a href="${contextPath }/member/list">MEMBERS</a>
	<a href="${contextPath }/board/boardAllList">BOARD</a>
	<c:if test="${login != null }">
	<a href="${contextPath }/member/logout">LOGOUT</a>
	</c:if>
	<c:if test="${login == null }">
	<a href="${contextPath }/member/login">LOGIN</a>
	</c:if>
	<hr>
</body>
</html>