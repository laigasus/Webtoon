<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach items="${webtoon}" var="webtoon">
	<c:set var="last" value="${webtoon.getImgSrc().length()-1}" />
	<div class="my-webtoon-container"
		onclick="location.href='${webtoon.getWebtoonUrl()}'">
		<img src="${webtoon.getImgSrc().substring(0,last)}" />
		<p>${webtoon.getWebtoonTitle()}</p>
	</div>
</c:forEach>