<%@page import="com.user.model.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
request.setCharacterEncoding("UTF-8");

String email = request.getParameter("email");
String password = request.getParameter("password");
String check=request.getParameter("saveCheck");   //아이디비번저장 체크박스가 체크되었는지를 확인하여서 체크되면 "saveCheck"="1" 를가져오고 아니면 null
if(check==null){ //equals 오류방지
	check="";
}
UserDAO dao = UserDAO.getInstance();
int result = dao.userCheck(email, password);
if(check.equals("checked")){  //check가 됐으면
	if (result == -1) {
		out.print("<script>");
		out.print("alert('없는 이메일입니다');");
		out.print("location.href = 'login.jsp';");
		out.print("</script>");
	} else if (result == 0) {
		out.print("<script>");
		out.print("alert('비밀번호를 다시 입력해주세요');");
		out.print("history.back();");
		out.print("</script>");
	} else { //로그인 성공 
		UserVO vo = dao.getUserInfo(email);
		session.setAttribute("session_user_email", email);
		session.setAttribute("session_user_password",password);
		session.setAttribute("session_user_nick", vo.getNick());
		application.setAttribute("chSave", "checked");  //chSave가 "checked" 을 가지면 login.jsp에서 체크상태가 유지됨
		application.setAttribute("saveId", email);
		application.setAttribute("savePw", password);
		response.sendRedirect("index.jsp");
	}
}else{  //체크 되면
	if (result == -1) {
		out.print("<script>");
		out.print("alert('없는 이메일입니다');");
		out.print("location.href = 'login.jsp';");
		out.print("</script>");
	} else if (result == 0) {
		out.print("<script>");
		out.print("alert('비밀번호를 다시 입력해주세요');");
		out.print("history.back();");
		out.print("</script>");
	} else { //로그인 성공 
		UserVO vo = dao.getUserInfo(email);
		session.setAttribute("session_user_email", email);
		session.setAttribute("session_user_password",password);
		session.setAttribute("session_user_nick", vo.getNick());
		application.removeAttribute("chSave");  //체크 안됐으니 지우기
		application.removeAttribute("saveId");
		application.removeAttribute("savePw");
		response.sendRedirect("index.jsp");
	}
}
%>
</body>
</html>
