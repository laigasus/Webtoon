<%@page import="java.sql.*"%>
<%@page import="com.user.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String email = request.getParameter("email");
	//전달 받은 이메일이 db에서 중복된 이메일인지 확인 후 true or false return
	UserDAO dao =UserDAO.getInstance();
	boolean check = dao.CheckDuplicate(email);
%>

<%
	if(check){
		//중복 이메일이 있으면 false를 등록
		session.setAttribute("check",false);
		response.sendRedirect("register.jsp");
	}else{
		//이메일 사용가능시 세션에 확인한 이메일과 check에 true값을 등록 
		session.setAttribute("check",true);
		session.setAttribute("checkedemail",email);
		response.sendRedirect("email_duplicate.jsp");
	}
%>
