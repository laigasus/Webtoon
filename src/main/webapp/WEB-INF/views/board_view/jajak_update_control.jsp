<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
<c:when test="${check}">
	<script>location.href='jajak.jsp'</script>
</c:when>
<c:otherwise>
	<script>
		alert("로그인해주세요");
		location.href="login.jsp";
	</script>
</c:otherwise>
</script>
</c:choose>