<%@page import="co.yedam.UserVO"%>
<%@page import="co.yedam.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ch05/register.jsp</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");//한글처리
	
	String uid = request.getParameter("user_id");
	String upw = request.getParameter("user_pw");
	String unm = request.getParameter("user_name");
	String gen = request.getParameter("user_gen");
	String uhb = request.getParameter("user_hobby");
	String ubt = request.getParameter("user_birth");
	
	UserVO vo = new UserVO();
	vo.setUserId(uid);
	vo.setUserPw(upw);
	vo.setUserName(unm);
	vo.setUserGen(gen);
	vo.setUserHobby(uhb);
	vo.setUserBirth(ubt);
	
	UserDAO dao = new UserDAO();
	dao.insertUser(vo);
	
	out.print("<script>alert('입력성공')</script>");
	out.print("<h2>회원가입을 완료하였습니다.</h2>");
	
	%>
</body>
</html>