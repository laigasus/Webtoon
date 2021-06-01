<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.crawling.model.*" %>
<% 
	request.setCharacterEncoding("UTF-8");
	String imgSrc = request.getParameter("imgSrc");
	String webtoonTitle = request.getParameter("webtoonTitle");
	String webtoonUrl = request.getParameter("webtoonUrl");
	String login;
	webtoonDAO dao = webtoonDAO.getInstance();
	if(session.getAttribute("session_user_email")==null){
		login = "";
	}else{
		login=(String)session.getAttribute("session_user_email");
	}
	int likeCheck=dao.myWebtoonCheck(webtoonTitle, login);  //like좋아요 했으면 1반환
	if(!login.equals("")&&likeCheck==1){  //좋아요를 이미 눌렀고 로그인을 한상황
		dao.myWebtoonDelete(webtoonTitle, login); // insert into 4가지 인자 삽입
		%> <script>alert("좋아요를 취소하였습니다.");</script><% 
	}else if(!login.equals("")){  //좋아요 안눌렀고 로그인 한상황
		dao.myWebtoonUpload(imgSrc, webtoonTitle, webtoonUrl, login); // insert into 4가지 인자 삽입
	}
	else{%>
		<script>
			alert("로그인 해주세요");
			location.href="login.jsp";
		</script>
	<%}%>

<script> history.go(-1)</script>