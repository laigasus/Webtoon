<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>footer.jsp</title>
</head>
<body>
	<footer>
		<div class="container">
			<div class="col-1">
				<img id="footer-logo" src="${path}/resources/img/footer-logo.svg" />
			</div>
			<div class="col-3">
				<div>
					<div>
						<h1>Also on TOON store</h1>
						<ul>
							<li><a href="/mypage">내 계정</a></li>
							<li><a href="/faq">자주 묻는 질문</a></li>
						</ul>
					</div>
					<div>
						<h1>Follow TOON store</h1>
						<div id="social-container">
							<ul>
								<li><a href="https://www.facebook.com"><img
										src="${path}/resources/img/social/facebook.svg" /></a></li>
								<li><a href="https://www.instagram.com"><img
										src="${path}/resources/img/social/instagram.svg" /></a></li>
								<li><a href="https://twitter.com"><img
										src="${path}/resources/img/social/twitter.svg" /></a></li>
								<li><a href="https://www.youtube.com"><img
										src="${path}/resources/img/social/youtube.svg" /></a></li>
								<li><a href="https://ko.wikipedia.org/wiki/RSS"><img
										src="${path}/resources/img/social/rss.svg" /></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div>
					<p id="copyright">&copy; 2017 TOON store</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
