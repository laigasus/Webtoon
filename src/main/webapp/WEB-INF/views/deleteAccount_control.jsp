<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:choose>
	<c:when test="${result}==0">
        <script>
        alert('비밀번호를 다시 입력해주세요');
        location.href='/deleteAccount';
        </script>
    </c:when>
	<c:otherwise>
		<script>
			alert('회원탈퇴가 완료되었습니다');
			location.href='/index';
		</script>
    </c:otherwise>
</c:choose>
