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
id : ${mem.dto.id }<br>
pw : ${mem.dto.pw }<br>
addr : ${mem.addr1 }<br>
addr : ${mem.addr2 }<br>
addr : ${mem.addr3 }<br>
<hr>
<h3>수정</h3>
<form action="${contextPath}/member/modify" method="post">
      <input type="text" name="id" readonly value=${mem.dto.id}><br>
      <input type="text" name="pw" value=${mem.dto.pw}><br>
      <input type="text" readonly id = "addr1" name="addr" value=${mem.addr1}>
      <button type="button" onclick="daumPost()">우편번호 찾기</button><br>
      <input type="text" readonly id = "addr2" name="addr" value=${mem.addr2}><br>
      <input type="text" id="addr3" name="addr" value=${mem.addr3}><br>
      <input type="submit" value="수정"><br>
   </form>
</body>
</html>