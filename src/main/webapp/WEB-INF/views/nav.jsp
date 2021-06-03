<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>nav.jsp</title>
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css" />
<link rel="stylesheet" href="${path}/resources/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<nav>
		<div class="container">
			<a href="/"><img id="logo" src="${path}/resources/img/logo.svg" /></a>
			<ul id="nav-container">
				<li><a href="/naver">네이버</a></li>
				<li><a href="/daum">다음</a></li>
				<li><a href="/nate">네이트</a></li>
				<li><a href="/jajak">커뮤니티</a></li>
				<div id="nav-icon">
					<li id="user"><img src="${path}/resources/img/button/nav/user.svg" id="user-img" /><img
						src="${path}/resources/img/button/nav/close.svg" id="user-close" />
					</li>
					<li id="search"><img src="${path}/resources/img/button/nav/search.svg"
						id="search-img" /><img src="${path}/resources/img/button/nav/close.svg"
						id="search-close" /></li>
				</div>
			</ul>
		</div>
		<div id="user-bar">
			<div class="container">
				<c:choose>
					<c:when test="${session_user_email == null}">
						<a href="/login"><button class="blue">로그인</button></a>
						<a href="/register"><button class="red">회원가입</button></a>
					</c:when>
					<c:when
						test="${session_user_email eq 'root@naver.com'}">
						<a href="/logout"><button class="red">로그아웃</button></a>
						<a href="/admin_page"><button class="blue">관리자페이지</button></a>
					</c:when>
					<c:otherwise>
						<span>${session_user_nick}님 환영합니다</span>
						<a href="/logout"><button class="red">로그아웃</button></a>
						<a href="/mypage"><button class="blue">마이페이지</button></a>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
		<div id="search-bar">
			<div class="container">
				<form action="/naver_search_result">
					<img src="${path}/resources/img/input/search.svg" /><input type="text" name="q"
						placeholder="제목 / 작가" />
					<button type="submit" class="blue">검색</button>
				</form>
			</div>
		</div>
	</nav>
</body>
</html>
