<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%
//admin_page.jsp에서 a 태그로 값을 받아서 mypage로 가기전에 넘겨주는 페이지입니다.
String admin_user_email= request.getParameter("admin_user_email");
session.setAttribute("admin_user_email", admin_user_email);
System.out.println("admin_user_mypage");
System.out.println(admin_user_email);

response.sendRedirect("mypage.jsp");
%>