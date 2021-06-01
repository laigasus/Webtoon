<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="com.comment.model.*"%>
<%
request.setCharacterEncoding("UTF-8");

if(session.getAttribute("session_user_email")==null){
	out.print("<script>");
	out.print("alert('로그인해주세요');");
	out.print("location.href = 'login.jsp';");
	out.print("</script>");
}else{
	String cm_content = (String) request.getParameter("cm_content");
	int Bd_num = Integer.parseInt(request.getParameter("Bd_num"));
	String cm_writer = (String) request.getParameter("nick");

	CommentService.getInstance().regist(Bd_num, cm_writer, cm_content,(String)session.getAttribute("session_user_email"));
	response.sendRedirect("jajak_content.jsp?Bd_num="+Bd_num);
}

%>
