<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.board.model.*" %>
<%

	int Bd_num=Integer.parseInt(request.getParameter("Bd_num"));
	BoardDAO.getInstance().deleteBoard(Bd_num);	
	response.sendRedirect("mypage.jsp"); 
%>