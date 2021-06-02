<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%-- session에 등록된 check 값에 따라 이메일 중복여부 확인 --%>

<c:choose>
	<c:when test="${check}==null">
		<form id="emailduplicate-form" action="email_duplicate_control.jsp">
			<input type="email" name="email" placeholder="이메일" /> <input
				class="inputSearch" type="submit" value="검색" />
		</form>
		<p>이메일 중복을 체크해주세요.</p>
	</c:when>
	<c:when test="${check}==true">
		<form id="emailduplicate-form" action="email_duplicate_control.jsp">
			<input type="email" name="email" placeholder="이메일" /> <input
				type="submit" value="검색" />
		</form>
		<p>사용가능한 이메일입니다.</p>
	</c:when>
	<c:otherwise>
		<form id="emailduplicate-form" action="email_duplicate_control.jsp">
			<input type="email" name="email" placeholder="이메일" /> <input
				class="inputSearch" type="submit" value="검색" />
		</form>
		<p>사용 불가능한 이메일입니다.</p>
	</c:otherwise>
</c:choose>
