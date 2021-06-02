<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>자작툰 - 툰스토어</title>
<meta charset="UTF-8" />
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			커뮤니티 <img src="${path}/resources/img/menu/jajak.svg" />
		</div>
	</header>
	<section id="jajak-section">
		<div class="container">
			<h1>
				<img src="${path}/resources/img/button/arrow.svg" /> 커뮤니티
			</h1>
			<ul class="tabs" id="jajak-tab">
				<form method="post" action="/jajak">
					<select name="category">
						<option selected value="bd_title">제목</option>
						<option value="bd_writer">작성자</option>
					</select> 
					<input type="text" name="search" placeholder="검색어 입력" />
					<button id="jajak-serach-button" type="submit">검색</button>
				</form>
			</ul>

			<table>
				<thead>
					<tr>
						<th width="10%">번호</th>
						<th width="50%">제목</th>
						<th width="10%">조회수</th>
						<th width="15%">작성자</th>
						<th width="15%">작성일</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${1 eq 1}">
					<c:forEach items="${articlesAdmin}" var="article">
						<tr style="background-color: #fff7fa; color: red;">
							<td width="10%">[공지]</td>
							<td width="50%"><a
								href="/jajak_content?bd_num=${article.getBd_num()}&nick=${article.getBd_writer()}"
								class="black_a" style="color: red;">${article.getBd_title()}</a></td>
							<td width="10%">${article.getBd_view()}
							<td width="15%">${article.getBd_writer()}</td>
							<td width="15%">${article.getBd_date()}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${search eq null}">  <!-- 이거 맞는지 모르겠음 -->
					<c:forEach items="${articles}" var="article">
						<tr>
							<td width="10%">${article.getBd_num()}</td>
							<td width="50%"><a
								href="/jajak_content?bd_num=${article.getBd_num()}&nick=${article.getBd_writer()}"
								class="black_a">${article.getBd_title() }</a></td>
							<td width="10%">${article.getBd_view()}
							<td width="15%">${article.getBd_writer()}</td>
							<td width="15%">${article.getBd_date() }</td>
						</tr>
					</c:forEach>
				</c:when>
				
				</c:choose>
					<tr id="jajak-paging-tr">
						<td colspan="6" align="center">
						<!-- 페이징 처리 일단  생략 -->
						1
						</td>
					</tr>
				</tbody>
			</table>
			<a href="/jajak-upload">
				<button class="blue">게시글 올리기</button>
			</a>
		</div>
	</section>
	<jsp:include page="footer.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<a href="#nav-container" id="top"><img src="${path}/resources/img/button/top.svg" /></a>
	<script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
	<script src="${path}/resources/js/script.js"></script>
</body>
</html>
