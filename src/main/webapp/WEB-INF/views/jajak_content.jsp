<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="com.board.model.*" %>
<%@page import="com.comment.model.*"%>
<%@page import="com.user.model.*" %>
<%
	int Bd_num=Integer.parseInt(request.getParameter("Bd_num")); //String 으로 받은 Bd_num을 int로 변수에 저장
	String nick=(String)session.getAttribute("session_user_nick");
	String email=(String)session.getAttribute("session_user_email");  //현재 이메일 가져오기
	if(email==null){  //널값 방지
		email="";
	}
%>
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
      <div class="container">커뮤니티<img src="img/menu/jajak-upload.svg"/></div>
    </header>
    <section>
      <div class="container">
        <h1><img src="img/button/arrow.svg"/> 커뮤니티 </h1>
        <%
        BoardVO articles=BoardDAO.getInstance().contentBoard(Bd_num);
        BoardDAO.getInstance().viewIncrease(articles.getBd_num(),articles.getBd_view());//viewIncrease 로 값증가
        String writer = UserDAO.getInstance().getUserInfo(articles.getBd_email()).getNick();
        %>
		<div class="jajak_content_header">
			<h3><%=articles.getBd_title() %></h3>
			<div class="jajak_content_header_infor">등록일:<%=articles.getBd_date()%>조회수:<%=articles.getBd_view()%>작성자:<%=writer %></div>
			<br>
			<hr>
			<br><br>
			<%=articles.getBd_content() %>
		</div>
		<br><br>
		<% if(email.equals(articles.getBd_email())) {  //현재 email과 작성자일치하는가 %>
		<div class="jajak_content_footer ">
			<button class="red" onclick="alert('글이 삭제되었습니다'); location.href='jajak_content_delete.jsp?Bd_num=<%=Bd_num%>'">삭제</button>
			<button class="blue" onclick="location.href='jajak-update.jsp?content_number=<%=Bd_num%>'">수정</button>
      	</div>
      	<% } else if(email.equals("root@naver.com")) { %>
		<div class="jajak_content_footer ">
			<button class="red" onclick="alert('글이 삭제되었습니다'); location.href='jajak_content_delete.jsp?Bd_num=<%=Bd_num%>'">삭제</button>
      	</div>
      	<% } %>
      	<form action="jajak_content_control.jsp" method="post">
			<input type="hidden" name="Bd_num" value="<%=Bd_num%>">
			<input type="hidden" name="nick" value="<%=nick%>">
      	<br><h1 id="comment-h1"><img src="img/button/arrow.svg" /> 댓글 </h1><br>
			<table style="text-align:center; ">
			<%if(CommentDAO.getInstance().listComment(Bd_num).isEmpty()) {%>
				<thead>
					<tr><th width="50%">
					<tr><th>댓글이 없어요</th></tr>
					<tr><th><img src="img/zzz.svg"></th></tr>
				</thead>
			<%}else{ %>
			<thead>
			</thead>
			<tbody>
 					<%CommentVO best=CommentDAO.getInstance().bestComment(Bd_num);
 					 String best_commentor = UserDAO.getInstance().getUserInfo(best.getEmail()).getNick();
 					%>
				<tr style="background-color: #ffe2f4;">
					<td width="10%"><%=best_commentor%><br>
					<%=best.getCm_date()%></td>
					<td width="50%"><%=best.getCm_content()%></td>
					<td width="5%"><%=best.getCm_like()%>
					<a href="comment_like_control.jsp?bd_num=<%=Bd_num%>&cm_id=<%=best.getCm_id()%>&writer=<%=writer%>"> 
					<img src="img/love.svg"></a></td>
				</tr>
				<%
					for (CommentVO comments : CommentDAO.getInstance().listComment(Bd_num)) {
						String commentor = UserDAO.getInstance().getUserInfo(comments.getEmail()).getNick();
						//listBoard()에 있는거를 static하게 이용하는
				%>
				<tr>
					<td width="10%"><%=commentor%><br>
					<%=comments.getCm_date()%></td>
					<td width="50%"><%=comments.getCm_content()%></td>
					<td width="5%"><%=comments.getCm_like()%>
					<a href="comment_like_control.jsp?bd_num=<%=Bd_num%>&cm_id=<%=comments.getCm_id()%>&writer=<%=writer%>">   
					<img src="img/love.svg"></a></td>
				</tr>
				<%
					}
				%>
			</tbody>
			<%} %>
				<tfoot>
				<tr>
					<td style="text-align: center;"><%=nick%></td>
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
    <a href="#nav-container" id="top"><img src="img/button/top.svg" /></a>
    <script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>
