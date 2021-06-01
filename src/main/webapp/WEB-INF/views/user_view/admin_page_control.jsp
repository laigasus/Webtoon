<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
	out.print("<script>");
	out.print("alert('회원탈퇴가 완료되었습니다');");
	out.print("location.href='admin_page.jsp';");
	out.print("</script>");
	%>
</body>
</html>