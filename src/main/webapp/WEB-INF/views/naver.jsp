<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>네이버 웹툰 - 툰스토어</title>
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			네이버<br>웹툰<img src="${path}/resources/img/menu/site.svg" />
		</div>
	</header>
	<section>
		<div class="container">
			<h1>
				<img src="${path}/resources/img/button/arrow.svg" /> 네이버 웹툰
			</h1>

			<ul id="day-tab">
				<c:forEach var="i" begin="0" end="${fn:length(weekArr)-1}" step="1">
					<li class="${weekArr[i]}"><a
						href="/naver?choosedDay=${dayOfWeekKor[i]}">${dayOfWeekKor[i]}</a>
						<img src="${path}/resources/img/day/${dayOfWeekKor[i]}.svg" /></li>
				</c:forEach>
			</ul>
			<div class="list-container">
				<c:forEach items="${articles}" var="article">
					<a class="toonlist-a" href="/toon_list?URL=${article.getUrl()}">
						<img src="${article.getThumb()}" />
						<ul>
							<li class="title">${article.getTitle()}</li>
						</ul>
					</a>
				</c:forEach>
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
