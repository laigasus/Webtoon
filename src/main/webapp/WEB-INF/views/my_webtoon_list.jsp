<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.crawling.model.*" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String email = (session.getAttribute("session_user_email")!=null)? (String)session.getAttribute("session_user_email"):"";
	if(!email.equals(null)){
		try{
			for(my_webtoon webtoon : webtoonDAO.getInstance().getMyWebtoonList(email)){
			int last = webtoon.getImgSrc().length()-1;
			%>
			<div class="my-webtoon-container" onclick="location.href='<%=webtoon.getWebtoonUrl()%>'">
				<img src="<%= webtoon.getImgSrc().substring(0,last)%>" />
				<p> <%= webtoon.getWebtoonTitle() %></p>
			</div>
		<% }
	}catch(Exception e){
		e.printStackTrace();
	}
}
%>