<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isErrorPage = "true" %>
<%@page import="com.external.module.*"%>
<%response.setCharacterEncoding("UTF-8");
int errCode=(int)request.getAttribute("javax.servlet.error.status_code");%>
<!DOCTYPE html>
<html>
  <head>
    <title>툰스토어</title>
  </head>
  <body>
    <header style="text-align:center:">
      <h1>저런!!</h1>
    </header>
    <section>
      <div class="container" style="text-align: center;">
        <img src="img/not_found.svg" />
        <h1> ${requestScope['javax.servlet.error.status_code']}</h1>
        <h3><%=ErrorList.printError(errCode)%></h3>
        </div>
    </section>
    <a href="#nav-container" id="top"><img src="img/button/top.svg" /></a>
  </body>
</html>
