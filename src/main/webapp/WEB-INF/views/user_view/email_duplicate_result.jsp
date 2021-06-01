<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%
	request.setCharacterEncoding("utf-8");
	boolean check = Boolean.parseBoolean(request.getParameter("check"));
	String checkedEmail = request.getParameter("checkedemail");
%>
<%
	if(check){
		//중복 이메일이 있으면 false를 등록
		session.setAttribute("check",false);
		response.sendRedirect("register.jsp");
	}else{
		//이메일 사용가능시 세션에 확인한 이메일과 check에 true값을 등록 
		session.setAttribute("check",true);
		session.setAttribute("checkedemail",checkedEmail);
		response.sendRedirect("email_duplicate.jsp");
	}
%>