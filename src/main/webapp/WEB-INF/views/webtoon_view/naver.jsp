<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>네이버 웹툰 - 툰스토어</title>
</head>
<body>
	<jsp:include page="../etc/nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			네이버<br>웹툰<img src="img/menu/site.svg" />
		</div>
	</header>
	<section>
		<div class="container">
			<h1>
				<img src="img/button/arrow.svg" /> 네이버 웹툰
			</h1>

			<ul id="day-tab">
				<c:forEach begin="0" items="${weekArr}" var="i"
					end="${weekArr.length}" step="1">
					<li class="${weekArr[i]}"><a
						href="naver.jsp?choosedDay=${CalculateDate.dayOfWeekKor[i]}">${CalculateDate.dayOfWeekKor[i]}</a>
						<img src="img/day/${CalculateDate.dayOfWeekKor[i]}.svg" /></li>
				</c:forEach>
			</ul>
			<div class="list-container">
				<c:forEach items="${articles}" var="article">
					<a class="toonlist-a" href="toon_list.jsp?URL=${article.getUrl()}">
						<img src="${article.getThumb()}" />
						<ul>
							<li class="title">${article.getTitle()}</li>
						</ul>
					</a>
				</c:forEach>
			</div>
		</div>
	</section>

	<jsp:include page="../etc/footer.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<a href="#nav-container" id="top"><img src="img/button/top.svg" /></a>
	<script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/script.js"></script>
</body>
</html>
