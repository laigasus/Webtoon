<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.user.model.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String email = (String)session.getAttribute("session_user_email");
	String password = (String)session.getAttribute("session_user_password");
	String nickname= request.getParameter("nickname");
	UserVO vo = new UserVO(email,nickname,password);
	UserDAO.getInstance().updateUser(vo);
	session.removeAttribute("session_user_nick");
	session.setAttribute("session_user_nick",nickname);
%>
<script>
	location.href="mypage.jsp"
</script>