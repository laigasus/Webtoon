<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>자작툰 올리기 - 툰스토어</title>
    <meta charset="UTF-8" />
  </head>
  <body>
    <jsp:include page="nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <header>
      <div class="container">커뮤니티<img src="${path}/resources/img/menu/jajak-upload.svg"/></div>
    </header>
    <section>
      <div class="container">
        <h1><img src="${path}/resources/img/button/arrow.svg"/> 커뮤니티 </h1>
			<div class="jajak_content_header">
				<h3>${articles.getBd_title()}</h3>
				<div class="jajak_content_header_infor">등록일:${articles.getBd_date()}조회수:${articles.getBd_view()}작성자:${articles.getBd_writer()}</div>
				<br>
				<hr>
				<br><br>
				${articles.getBd_content()}
			</div>
			<br><br>
			<c:choose>
			<c:when test="${email eq articles.getBd_email()}">
				<div class="jajak_content_footer ">
					<button class="red" onclick="alert('글이 삭제되었습니다'); location.href='jajak_content_delete?Bd_num=${Bd_num}'">삭제</button>
					<button class="blue" onclick="location.href='jajak_update?content_number=${Bd_num}'">수정</button>
		      	</div>
			</c:when>
			<c:when test="${email eq 'root@naver.com'}">
				<div class="jajak_content_footer ">
					<button class="red" onclick="alert('글이 삭제되었습니다'); location.href='jajak_content_delete?Bd_num=${Bd_num}'">삭제</button>
		      	</div>
			</c:when>
			</c:choose>
      	
      	<form action="jajak_content_control" method="post">
			<input type="hidden" name="Bd_num" value="${Bd_num}">
			<input type="hidden" name="nick" value="${nick}">
      	<br><h1 id="comment-h1"><img src="${path}/resources/img/button/arrow.svg" /> 댓글 </h1><br>
			<table style="text-align:center; ">
			<c:choose>
			<c:when test="${commentEmpty}">
				<thead>
					<tr><th width="50%">
					<tr><th>댓글이 없어요</th></tr>
					<tr><th><img src="${path}/resources/img/zzz.svg"></th></tr>
				</thead>
			</c:when>
			<c:otherwise>
			<thead>
			</thead>
			<tbody>
			<c:choose>
			<c:when test="${best eq best}">
				<c:forEach items="${best}" var="best">
					<tr style="background-color: #ffe2f4;">
						<td width="10%">${best_commentor}<br>
						${best.getCm_date()}</td>
						<td width="50%">${best.getCm_content()}</td>
						<td width="5%">${best.getCm_like()}
						<a href="comment_like_control?bd_num=${Bd_num}&cm_id=${best.getCm_id()}&writer=${writer}"> 
						<img src="${path}/resources/img/love.svg"></a></td>
					</tr>
				</c:forEach>
			</c:when>
			</c:choose>
				<c:forEach items="${comments}" var="comment">
					<tr>
						<td width="10%">"작성자"<br>
						${comment.getCm_date()}</td>
						<td width="50%">${comment.getCm_content()}</td>
						<td width="5%">${comment.getCm_like()}
						<a href="comment_like_control?bd_num=${Bd_num}&cm_id=${comment.getCm_id()}&writer=${writer}">   
						<img src="${path}/resources/img/love.svg"></a></td>
					</tr>
				</c:forEach>
			</tbody>
			</c:otherwise>
			</c:choose>
			
				<tfoot>
				<tr>
					<td style="text-align: center;">${nick }</td>
					<td><textarea style="resize: none; text-area:left;" name="cm_content" rows="4" cols="70" ></textarea></td>
					<td style="text-align: center;">
					<input id="jajak-input-button" type="submit" value="등록"></td>
				</tr>
			</tfoot>
		</table>
		</form>
      </div>
    </section>
    <jsp:include page="footer.jsp">
      <jsp:param name="name" value="value"/>
    </jsp:include>
    <a href="#nav-container" id="top"><img src="${path}/resources/img/button/top.svg" /></a>
    <script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
    <script src="${path}/resources/js/script.js"></script>
  </body>
</html>
