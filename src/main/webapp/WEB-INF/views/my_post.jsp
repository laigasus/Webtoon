<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach var="post" items="${post}">

	<div class="my-post-container"
		onclick="location.href='/jajak_content?Bd_num=${post.getBd_num}'"
		style="cursor: pointer">
		<div>${post.getBd_date()}</div>
		<div>${post.getBd_title()}</div>
		<div>${post.getBd_view()}</div>
	</div>

</c:forEach>

