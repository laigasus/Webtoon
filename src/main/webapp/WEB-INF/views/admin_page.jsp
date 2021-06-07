<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>관리자페이지</title>
<meta charset="UTF-8" />
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			관리자페이지<img src="${path}/resources/img/menu/mypage.svg" />
		</div>
	</header>
	<section id="jajak-section">
		<div class="container">
			<h1>
				<img src="${path}/resources/img/button/arrow.svg" /> 커뮤니티
			</h1>
			<form action="/admin_page_control" method="post">
				<table>
					<thead>
						<tr>
							<th width="10%"><input type="checkbox" id="all_select" /><label
								for="all_select"></label></th>
							<th width="50%">이메일</th>
							<th width="10%">비밀번호</th>
							<th width="15%">닉네임</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td width="10%"><input type="checkbox" name="email"
									value="${user.getEmail()}"><label for="select_row"></label></td>
								<td width="50%"><a class="black_a"
									href="/admin_user_mypage?admin_user_email=${user.getEmail()}">${user.getEmail()}</a></td>
								<td width="20%">${user.getPw()}</td>
								<td width="20%">${user.getNick()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="submit" class="blue">계정 삭제</button>
			</form>
		</div>
	</section>
	<jsp:include page="footer.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<a href="#nav-container" id="top"><img src="${path}/resources/img/button/top.svg" /></a>

	<script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
	<script src="${path}/resources/js/script.js"></script>

	<script>
		$('#all_select').click(function() {//전체체크코드  
			if ($("input:checkbox[id='all_select']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		});
	</script>
</body>
</html>
