<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:choose>
	<c:when test="!${login}.equals('')&&${likeCheck}==1">
		<script>
			alert("좋아요를 취소하였습니다.");
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("로그인 해주세요");
			location.href = "login.jsp";
		</script>
	</c:otherwise>
</c:choose>

<script>
	history.go(-1)
</script>