<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	String chSave=(String)application.getAttribute("chSave"); //체크했으면 checked 저장
	String saveId=(String)application.getAttribute("saveId");  //saveId
	String savePw=(String)application.getAttribute("savePw");
	if(saveId==null){ //saveId가 비어있어서 null이 보여지는 것 방지
		saveId="";
		savePw="";
	}
%>
<!DOCTYPE >
<html>
  <head>
    <title>로그인 - 툰스토어</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/user.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="container">
      <a href="index.jsp"><img src="img/menu/user.svg" /></a>
      <form action="login_control.jsp" method="post">
        <div class="form-input">
          	<input type="email" name="email" placeholder="이메일" value="<%=saveId%>"required/>
        </div>
        <div class="form-input">
          	<input type="password" name="password" placeholder="비밀번호" value="<%=savePw%>" required />
        </div>
        <div class="form-input-checkbox">
        	<input type="checkbox" name="saveCheck" value="checked" <%=chSave %>/><span>아이디/비밀번호저장</span>
        </div>
        <input type="submit" value="로그인" />
        <div class="link">
          <a href="pw_find.jsp">비밀번호 찾기</a>
          <a href="register.jsp">회원가입</a>
        </div>
      </form>
    </div>
  </body>
</html>
