<%@page import="com.user.model.UserDAO"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String email = (String) session.getAttribute("session_user_email");
String password = request.getParameter("password");

int result = UserDAO.getInstance().userCheck(email, password);

if (result == 0) {
	out.print("<script>");
	out.print("alert('비밀번호를 다시 입력해주세요');");
	out.print("location.href='deleteAccount.jsp';");
	out.print("</script>");
} else {
	UserDAO.getInstance().deleteUser(email);

	session.invalidate();

	out.print("<script>");
	out.print("alert('회원탈퇴가 완료되었습니다');");
	out.print("location.href='index.jsp';");
	out.print("</script>");
}
%>