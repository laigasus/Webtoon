<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.user.model.*" %>
<%
request.setCharacterEncoding("UTF-8");
String login=(String)session.getAttribute("session_user_email"); 
String password = (String)session.getAttribute("session_user_password");
String nick=(String)session.getAttribute("session_user_nick");  //login_control에서 set 했음
//이메일이 존재하면 로그인한거 
UserVO vo = UserDAO.getInstance().getUserInfo(login);
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>nav.jsp</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>    
  </head>
  <body>
    <nav>
      <div class="container">
        <a href="index.jsp"><img id="logo" src="img/logo.svg" /></a>
        <ul id="nav-container">
          <li><a href="naver.jsp">네이버</a></li>
          <li><a href="daum.jsp">다음</a></li>
          <li><a href="nate.jsp">네이트</a></li>
          <li><a href="jajak.jsp">커뮤니티</a></li>
          <div id="nav-icon">
            <li id="user">
              <img src="img/button/nav/user.svg" id="user-img" /><img
                src="img/button/nav/close.svg"
                id="user-close"
              />
            </li>
            <li id="search">
              <img src="img/button/nav/search.svg" id="search-img" /><img
                src="img/button/nav/close.svg"
                id="search-close"
              />
            </li>
          </div>
        </ul>
      </div>
      <div id="user-bar">
        <div class="container">
        <%if(login==null){ //로그인 안했으면 %>
          <a href="login.jsp"><button class="blue">로그인</button></a>
          <a href="register.jsp"><button class="red">회원가입</button></a>
        <%} else if(login.equals("root@naver.com") && password.equals("root")) { %>
        	<a href="logout.jsp"><button class="red">로그아웃</button></a>
        	<a href="admin_page.jsp"><button class="blue">관리자페이지</button></a>
        <%} else{ %>
          <span><%=vo.getNick() %>님 환영합니다</span>
          <a href="logout.jsp"><button class="red">로그아웃</button></a>
          <a href="mypage.jsp"><button class="blue">마이페이지</button></a>
        <%} %>
        </div>
      </div>
      <div id="search-bar">
        <div class="container">
          <form action="naver_search_result.jsp">
            <img src="img/input/search.svg" /><input type="text" name="q" placeholder="제목 / 작가"/>
            <button type="submit" class="blue">검색</button>
          </form>
        </div>
      </div>
    </nav>
  </body>
</html>
