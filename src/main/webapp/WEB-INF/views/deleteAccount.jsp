<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE >
<html>
<head>
<title>로그인 - 툰스토어</title>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="${path}/resources/css/user.css" />
</head>
<body>
	<div class="container">
		<a href="/"><img src="${path}/resources/img/menu/user.svg" /></a>
		<form action="/deleteAccount_control" method="post">
			<h2 style="color: white;">회원 탈퇴</h2>
			<div class="form-input">
				<input type="password" name="password" placeholder="비밀번호" required />
			</div>
			<input type="submit" value="확인" />
		</form>
	</div>
</body>
</html>
