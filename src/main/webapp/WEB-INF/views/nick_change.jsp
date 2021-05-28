<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<!DOCTYPE >
<html>
  <head>
    <meta charset="utf-8" />
    <title>닉네임 변경 - 툰스토어</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/user.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="container">
      <br>
      <a href="index.jsp"><img src="img/menu/user.svg" /></a>
      <form action="nick_change_control.jsp" method="post">
        <div class="form-input">
          <input type="text" name="nickname" placeholder="닉네임" required />
        </div>        
        <input type="submit" value="닉네임 변경" />
      </form>
    </div>
  </body>
</html>