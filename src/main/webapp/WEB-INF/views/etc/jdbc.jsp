<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="java.sql.*"%>
<%@page import="com.user.model.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String email = request.getParameter("email");
	String nickname = request.getParameter("nickname");
	String password = request.getParameter("password"); 
	String password_confirm = request.getParameter("password_confirm"); 
	Boolean check = false;
	if((Boolean)session.getAttribute("check")!=null){
		check=(Boolean)session.getAttribute("check");
	}
	String checkedEmail = (String)session.getAttribute("checkedemail");
%>

<%
UserVO vo = new UserVO(email,password,nickname);
UserDAO dao = UserDAO.getInstance();
if (password.equals(password_confirm)){
	//이메일 중복 체크 및 확인한 이메일과 가입요청한 이메일이 동일한지 확인
	if(check == true && email.equals(checkedEmail)){
		dao.insertUser(vo);
%>
		<script>
      		alert("회원가입을 완료하였습니다");
      		location.href = "login.jsp";
      		session.removeAttribute("check");
      		session.removeAttribute("checkedemail");
		</script>
<%		} else { %>
		<script>
			alert("이메일 중복체크를 해주세요");
			history.go(-1);
		</script>
		<%}
} else {%>
		<script>
      		alert("입력한 비밀번호가 일치하지 않습니다");
      		location.href = "register.jsp";
		</script>
	<% } %>



