<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
<c:choose>
	<c:when test="${session_user_email==null}">
		<script>
			alert('로그인해주세요');
			location.href = '/login';
		</script>
	</c:when>
	<c:otherwise>
		<script>
		history.go(-1);
		location.href = '/jajak_content';  // 원래 인자도 같이 보내고 싶은데 뭔가 잘안됨
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>


