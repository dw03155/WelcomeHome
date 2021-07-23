<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ch05/get.jsp</title>
</head>
<body>
	<% //자바쓰기
	String uid = request.getParameter("user_id");
	String upw = request.getParameter("user_pw");
	String unm = request.getParameter("user_name");
	%>
	<h3>ID: <%=uid %></h3>
	<h3>Password: <%=upw %></h3>
	<h3>Name: <%=unm %></h3>
</body>
</html>