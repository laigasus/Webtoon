<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<% request.setCharacterEncoding("utf-8"); 
String email = request.getParameter("email"); 
String password = request.getParameter("password"); 
String password_confirm = request.getParameter("password_confirm"); 
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title></title>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"
    />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <% if (password.equals(password_confirm)) { %>
    <script>
      alert("회원가입을 완료하였습니다");
      location.href = "login.jsp";
    </script>
    <% session.setAttribute("user_email", email);
    session.setAttribute("user_password", password); } else { %>
    <script>
      alert("입력한 비밀번호가 일치하지 않습니다");
      location.href = "register.jsp";
    </script>
    <% } %>
  </body>
</html>
