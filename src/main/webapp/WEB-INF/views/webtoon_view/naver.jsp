<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.external.module.CalculateDate"%>
<%@page import="com.naver.model.*"%>
<%String choosedDay=(String)request.getParameter("choosedDay");%>
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
      <div class="container"> 네이버<br>웹툰<img src="img/menu/site.svg" />
      </div>
    </header>
    <section>
      <div class="container">
        <h1><img src="img/button/arrow.svg"/> 네이버 웹툰</h1>
        <%       
        String[] weekArr={"", "", "", "", "", "", ""};        
        for(int i=1; i<=weekArr.length; i++){
        	if(choosedDay==null){
        		if(i==CalculateDate.dayOfWeek){
            		weekArr[i-1]="active";
            		break;
            	}
        	}else{
        		if(choosedDay.equals(CalculateDate.dayOfWeekKor[i-1])){
        			weekArr[i-1]="active";
        			break;
        		}
        	}        	
        }
        %>
        <ul id="day-tab">
        <%for(int i=0; i<7; i++){%>
        <li class="<%=weekArr[i]%>">
        <a href="naver.jsp?choosedDay=<%=CalculateDate.dayOfWeekKor[i]%>">
        <%=CalculateDate.dayOfWeekKor[i]%></a> <img src="img/day/<%=CalculateDate.dayOfWeekKor[i]%>.svg" /></li>
        <% }%>
        </ul>
        <div class="list-container">				
        <%
            for(naverVO articles: naverDAO.getInstance().listBoard(CalculateDate.calcDayOfWeek("kor", choosedDay))){
        %>
        <a class="toonlist-a" href="toon_list.jsp?URL=<%=articles.getUrl()%>">
            <img src="<%=articles.getThumb()%>" />
            <ul>
              <li class="title"><%=articles.getTitle()%></li>
              <!-- <li class="genre">액션 / 미스터리</li>
              <li class="author">신영우</li> -->
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
