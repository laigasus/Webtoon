<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<c:choose>
	<c:when test="${check eq 'checked'}">
		<c:choose>
			<c:when test="${result==-1}">
				<script>
					alert('없는 이메일입니다');
					location.href = '/login';
				</script>
			</c:when>
			<c:when test="${result==0}">
				<script>
					alert('비밀번호를 다시 입력해주세요');
					history.back();
				</script>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${result==-1}">
				<script>
					alert('없는 이메일입니다');
					location.href = '/login';
				</script>
			</c:when>
			<c:when test="${result==0}">
				<script>
					alert('비밀번호를 다시 입력해주세요');
					history.back();
				</script>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>