<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE >
<html>
  <head>
    <meta charset="utf-8" />
    <title>회원가입 - 툰스토어</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/user.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="regcontainer">
      <a href="/"><img src="${path}/resources/img/menu/user.svg" /></a>
      <div>
      	<jsp:include page="email_duplicate.jsp" flush="false"/>
      </div>
      <form action="/jdbc" method="post">
        <div class="form-input">
          <input type="email" name="email" placeholder="이메일" required />
        </div>
        <div class="form-input">
          <input
            type="password" name="password" placeholder="비밀번호" required />
        </div>
        <div class="form-input">
     	    <input type="password" name="password_confirm" placeholder="비밀번호 확인" required/>
        </div>
        <div class="form-input">
 	      	<input type="text" name="nickname" placeholder="닉네임" required/>
        </div>
        <input type="submit" value="회원가입" />
      </form>
    </div>
  </body>
</html>
