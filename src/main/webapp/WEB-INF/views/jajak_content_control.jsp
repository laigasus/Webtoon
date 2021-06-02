<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:choose>
	<c:when test="${session_user_email}==null">
		<script>
			alert('로그인해주세요');
			location.href = '/login';
		</script>
	</c:when>
	<c:otherwise>
		location.href = 'jajak_content.jsp?Bd_num=' + ${Bd_num};
	</c:otherwise>
</c:choose>


