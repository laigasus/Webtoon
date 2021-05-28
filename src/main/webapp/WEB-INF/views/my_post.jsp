<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.board.model.*" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String admin_user_email=(String)session.getAttribute("admin_user_email");
	String email="";
	if(admin_user_email==null){	admin_user_email="";}  //널이여도 equals 오류없게
	//사용자가 로그인하여 세션에 닉네임이나 이메일 둘중 프라이머리키가 등록되어있는 상태
	if(admin_user_email.equals("")){ //일반사용자가 접근
		email = (session.getAttribute("session_user_email")!=null)? (String)session.getAttribute("session_user_email"):"";
	}else{  //관리자가 들어온 마이페이지
		email=admin_user_email;
	}
	if(!email.equals(null)){
		try{
			for(BoardVO post : BoardDAO.getInstance().myListBoard(email)){%>
			<div class="my-post-container" onclick="location.href='jajak_content.jsp?Bd_num=<%=post.getBd_num() %>'" style="cursor:pointer">
				<div> <%= post.getBd_date() %></div>
				<div> <%= post.getBd_title() %></div>
				<div> <%= post.getBd_view() %></div>			
			</div>
			<% }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
