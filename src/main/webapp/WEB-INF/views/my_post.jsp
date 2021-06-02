<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
	<c:when test="!${email}.equals(null)">
		<script>
			alert('비밀번호를 다시 입력해주세요');
			location.href = '/deleteAccount';
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert('회원탈퇴가 완료되었습니다');
			location.href = '/index';
		</script>
	</c:otherwise>
</c:choose>

<c:forEach var="post" items="${post}">

	<div class="my-post-container"
		onclick="location.href='/jajak_content?Bd_num=${post.getBd_num}'"
		style="cursor: pointer">
		<div>${post.getBd_date()}</div>
		<div>${post.getBd_title()}</div>
		<div>${post.getBd_view()}</div>
	</div>

</c:forEach>

