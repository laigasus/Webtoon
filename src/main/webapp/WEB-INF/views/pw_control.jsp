<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<c:choose>
	<c:when test="${session_user_email}==null">
		<c:choose>
			<c:when test="${userCheck}==1">
				<script>
					alert('회원님의 임시비밀번호가 이메일로 보내졌습니다.');
				</script>
			</c:when>
			<c:otherwise>
				<script>
					alert('유효하지 않은 이메일입니다.');
					location.href = '/pw_find';
				</script>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${session_user_email}.equals(${email})">
				<script>
					alert('회원님의 임시비밀번호가 이메일로 보내졌습니다.');
				</script>
			</c:when>
			<c:otherwise>
				<script>
					alert('유효하지 않은 이메일입니다.');
					location.href = '/pw_find';
				</script>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title></title>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${check}==0">
			<script>
				alert("잘못된 값을 입력하셨습니다.");
				location.href = "/pw_find";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("비밀번호 변경 완료");
				location.href = "/pw_after";
			</script>
		</c:otherwise>
	</c:choose>

</body>
</html>
