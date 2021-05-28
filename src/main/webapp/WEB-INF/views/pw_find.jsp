<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<!DOCTYPE >
<html>
  <head>
    <meta charset="utf-8" />
    <title>비밀번호 찾기 - 툰스토어</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/user.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="container">
      <br>
      <a href="index.jsp"><img src="img/menu/user.svg" /></a>
      <form action="pw_control.jsp" method="post">
        <div class="form-input">
          <input type="email" name="email" placeholder="이메일" required />
        </div>        
        <input type="submit" value="비밀번호변경" />
      </form>
    </div>
  </body>
</html>
