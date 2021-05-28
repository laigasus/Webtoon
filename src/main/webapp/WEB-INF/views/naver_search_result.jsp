<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.external.module.CalculateDate"%>
<%@page import="com.naver.model.*"%>
<%String searchParam=(String)request.getParameter("q");%>
<!DOCTYPE html>
<html>
  <head>
    <title>네이버 웹툰 - 툰스토어</title>
  </head>
  <body>
    <jsp:include page="nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <header>
      <div class="container">
       검색<br />결과
        <img src="img/menu/site.svg" />
      </div>
    </header>
    <section>
      <div class="container">
        <h1><img src="img/button/arrow.svg" /> 검색결과</h1>    
        <div class="list-container">		
        <%
            for(naverVO articles: naverDAO.getInstance().searchBoard(searchParam)){
        %>
        <a href="toon_list.jsp?URL=<%=articles.getUrl()%>">  
            <img src="<%=articles.getThumb()%>" />
            <ul>
              <li class="title"><%=articles.getTitle()%></li>
            </ul>
          </a>			
		<% }%>
        </div>
      </div>
    </section>
    <jsp:include page="footer.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <a href="#nav-container" id="top"><img src="img/button/top.svg" /></a>
    <script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>
