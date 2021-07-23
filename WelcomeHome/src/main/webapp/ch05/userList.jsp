<%@page import="co.yedam.UserVO"%>
<%@page import="java.util.List"%>
<%@page import="co.yedam.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ch05/userList.jsp</title>
</head>
<body>
	<%
	//jsp 파일은 ctrl+shift+O 대신 ctrl+space
	UserDAO dao = new UserDAO();
	List<UserVO> list = dao.getUsers();
	out.println("<table border ='1'>");
	for (UserVO vo : list) {
		out.println("<li>" + vo.getUserName() + "</li>");
		out.println();//출력 //jsp에 이미 선언되어있는 객체
		out.println("<tr><td>" + vo.getUserId()//
		+ "</td><td>" + vo.getUserName()//
		+ "</td><td><input type='date' value='" + vo.getUserBirth() + "'>" //
		+ "</td></tr>");
	}
	out.println("</table>");
	%>
</body>
</html>