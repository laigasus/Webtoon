<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지 - 툰스토어</title>
<meta charset="UTF-8" />
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			마이페이지 <img src="${path}/resources/img/menu/mypage.svg" />
		</div>
	</header>
	<section>
		<div class="container">
			<h1>
				<img src="${path}/resources/img/button/arrow.svg" /> 마이페이지
			</h1>
			<div class="inner-container">
				<div class="my_post">
					<h4>나의 작성글</h4>
					<ul>
						<li>작성일</li>
						<li>제목</li>
						<li>조회수</li>
					</ul>
					<c:forEach var="post" items="${post}">
						<div class="my-post-container"
							onclick="location.href='/jajak_content?bd_num=${post.getBd_num()}'"
							style="cursor: pointer">
							<div>${post.getBd_date()}</div>
							<div>${post.getBd_title()}</div>
							<div>${post.getBd_view()}</div>
						</div>
					</c:forEach>

				</div>
				<div class="my_webtoon_list">
					<h4>찜한 웹툰</h4>
					<c:forEach items="${webtoons}" var="webtoon">
						<div class="my-webtoon-container"
							onclick="location.href='${webtoon.getMt_url()}'">
							<img src="${webtoon.getMt_imgsrc()}" />
							<p>${webtoon.getMt_title()}</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<br> <br>
			<div style="float: right">
				<button class="red" onclick="location.href='/nick_change'">닉네임
					변경</button>
				<button class="green" onclick="location.href='/pw_find'">내
					비밀번호 변경</button>
				<button class="blue" onclick="location.href='/deleteAccount'">회원
					탈퇴</button>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<a href="#nav-container" id="top"><img
		src="${path}/resources/img/button/top.svg" /></a>
	<script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
	<script src="${path}/resources/js/script.js"></script>
</body>
</html>
