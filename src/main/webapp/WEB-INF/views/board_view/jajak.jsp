<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="com.board.model.*"%>
<%@page import="com.user.model.*"%>
<%
String category = (String) request.getParameter("category");
String search = (String) request.getParameter("search");
if (category == null || search == null) {
	category = "제목";
	search = "";
}
int pageSize = 10;  //한페이지에 몇개의 글 출력할지 결정
String pageNum = request.getParameter("pageNum");
if (pageNum == null) {
	pageNum = "1";
}
int currentPage = Integer.parseInt(pageNum);

int gongziCount=1; //공지글의 갯수를  가져옵니다. 
int startRow = (currentPage - 1) * pageSize;  //현재페이지에 첫 번째 글
int count = 0;
BoardDAO forPage = BoardDAO.getInstance();
count = forPage.getCountBoard(); //총 레코드갯수 반환
List<BoardVO> list = null;
if (count > 0) {
	list = forPage.listBoard(startRow, pageSize);
}
System.out.println(startRow);
%>
<!DOCTYPE html>
<html>
<head>
<title>자작툰 - 툰스토어</title>
<meta charset="UTF-8" />
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			커뮤니티 <img src="img/menu/jajak.svg" />
		</div>
	</header>
	<section id="jajak-section">
		<div class="container">
			<h1>
				<img src="img/button/arrow.svg" /> 커뮤니티
			</h1>
			<ul class="tabs" id="jajak-tab">
				<form action="jajak.jsp">
					<select name="category">
						<option selected value="제목">제목</option>
						<option value="작성자">작성자</option>
					</select> <input type="text" name="search" placeholder="검색어 입력" />
					<button id="jajak-serach-button" type="submit">검색</button>
				</form>
			</ul>

			<table>
				<thead>
					<tr>
						<th width="10%">번호</th>
						<th width="50%">제목</th>
						<th width="10%">조회수</th>
						<th width="15%">작성자</th>
						<th width="15%">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
						if(currentPage==1){  //첫페이지에서만 공지출력
							for (BoardVO articles : BoardDAO.getInstance().AdminListBoard()) {
							String nick = UserDAO.getInstance().getUserInfo(articles.getBd_email()).getNick();
						%>
						<tr style="background-color: #fff7fa; color: red;">
							<td width="10%">[공지]</td>
							<td width="50%"><a
								href="jajak_content.jsp?Bd_num=<%=articles.getBd_num()%>&nick=<%=nick%>"
								class="black_a" style="color: red;"><%=articles.getBd_title()%></a></td>
							<td width="10%"><%=articles.getBd_view()%>
							<td width="15%"><%=UserDAO.getInstance().getUserInfo(articles.getBd_email()).getNick()%></td>
							<td width="15%"><%=articles.getBd_date()%></td>
						</tr>
						<%
							}
						}
					%>
					<%
						if (search.equals("")) {
						for (BoardVO articles : BoardDAO.getInstance().listBoard(startRow, pageSize)) {
							String nick = UserDAO.getInstance().getUserInfo(articles.getBd_email()).getNick();
					%>
					<tr>
						<td width="10%"><%=articles.getBd_num()%></td>
						<td width="50%"><a
							href="jajak_content.jsp?Bd_num=<%=articles.getBd_num()%>&nick=<%=nick%>"
							class="black_a"><%=articles.getBd_title()%></a></td>
						<td width="10%"><%=articles.getBd_view()%>
						<td width="15%"><%=nick%></td>
						<td width="15%"><%=articles.getBd_date()%></td>
					</tr>
					<%
						}
					} else {
					for (BoardVO articles : BoardDAO.getInstance().searchBoard(search, category)) {
					String nick = UserDAO.getInstance().getUserInfo(articles.getBd_email()).getNick();
					//listBoard()에 있는거를 static하게 이용하는
					%>
					<tr>
						<td width="10%"><%=articles.getBd_num()%></td>
						<td width="50%"><a
							href="jajak_content.jsp?Bd_num=<%=articles.getBd_num()%>&nick=<%=nick%>"
							class="black_a"><%=articles.getBd_title()%></a></td>
						<td width="10%"><%=articles.getBd_view()%>
						<td width="15%"><%=UserDAO.getInstance().getUserInfo(articles.getBd_email()).getNick()%></td>
						<td width="15%"><%=articles.getBd_date()%></td>
					</tr>
					<%
						}
					}
					%>
					<tr id="jajak-paging-tr">
						<td colspan="6" align="center">
						<%
							//페이징처리
						if (count > 0) {
							// 총 페이지의 수를 결정
							int pageCount = (count-gongziCount) / pageSize + ((count-gongziCount) % pageSize == 0 ? 0 : 1);
							// 한 화면안에 페이지에 갯수 넘으면 다음표시
							int pageBlock = 10;
							// 한 페이지에 보여줄 시작 및 끝 번호
							int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
							int endPage = startPage + pageBlock - 1;
							// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
							if (endPage > pageCount) {  //없으면 자료가 15개 있을때  번호가 [1]~[9] 까지 다나옴
								endPage = pageCount;
							}
							if (startPage > pageBlock) { // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
						%> <a href="jajak.jsp?pageNum=<%=startPage - 10%>">[이전]</a> <%
 							}
							 for (int i = startPage; i <= endPage; i++) { // 페이지 번호 결정
							 if (i == currentPage) { // 현재 페이지에는 링크를 설정하지 않음
							 %> [<%=i%>] <%
							 	} else { // 현재 페이지가 아닌 경우 링크 설정
							 %> <a href="jajak.jsp?pageNum=<%=i%>">[<%=i%>]
								</a> <%
							 	}
							 } 
							 if (endPage < pageCount) { // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
							 %> <a href="jajak.jsp?pageNum=<%=startPage + 10%>">[다음]</a> <%
							 	}
							 }
							 %>
						</td>
					</tr>
				</tbody>
			</table>
			<a href="jajak-upload.jsp">
				<button class="blue">게시글 올리기</button>
			</a>
		</div>
	</section>
	<jsp:include page="footer.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<a href="#nav-container" id="top"><img src="img/button/top.svg" /></a>
	<script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/script.js"></script>
</body>
</html>
