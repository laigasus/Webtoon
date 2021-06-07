<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>웹툰 소개 - 툰스토어</title>
<meta charset="UTF-8" />
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			웹툰<br />소개 <img src="${path}/resources/img/menu/detail.svg" />
		</div>
	</header>
	<section>
		<div class="container" id="detail">
			<h1>
				<img src="${path}/resources/img/button/arrow.svg" /> 웹툰 소개
			</h1>
			<div id="intro-top">
				<div>
					<img src="${InfoArr.get(0)}" />
				</div>
				<div>
					<div id="title">${InfoArr.get(1)}</div>
					<dl>
						<dt>장르</dt>
						<dd>${InfoArr.get(2)}</dd>
						<dt>작가</dt>
						<dd>${InfoArr.get(3)}</dd>
					</dl>
					<button type="button" class="blue" onclick="location.href='/cart'">장바구니
						담기</button>
					<br>
					<form style="display: none" method="post" action="/my_webtoon_add"
						id="frm">
						<input name="imgSrc" value="${InfoArr.get(0)}" /> <input
							name="webtoonTitle" value="${InfoArr.get(1)}" /> <input id="url"
							name="webtoonUrl" value="" />
					</form>
					<div class="like-button-div">
						<button type="submit" class="red"
							onclick="document.getElementById('frm').submit();">좋아요 ♥</button>
					</div>
				</div>
			</div>
			<div class="container">
				<h1>
					<img src="${path}/resources/img/button/arrow.svg" /> 커뮤니티
				</h1>
				<table>
					<thead>
						<tr>
							<th width="10%">썸네일</th>
							<th width="50%">제목</th>
							<th width="10%">등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${articles}" var="article">
							<tr class="cursorpointer"
								onClick="location.href='/detail?URL=${article.getUrl()}'">
								<td width="10%"><img src="${article.getThumb()}" /></td>
								<td width="50%">${article.getTitle()}</td>
								<td width="10%">${article.getDay()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
	<script>
		document.getElementById("url").value = document.location.href;
	</script>
</body>
</html>
