<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <title>웹툰 - 툰스토어</title>
    <meta charset="UTF-8" />
  </head>
  <body>
    <jsp:include page="nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <header>
      <div class="container">웹툰<br>소개<img src="img/menu/detail.svg" /></div>
    </header>
    <section>
      <div class="container" id="detail">
        <h1><img src="${path}/resources/img/button/arrow.svg"/> 웹툰 소개</h1>
        <div id="intro-top">
          <div>
            <img src="${InfoArr.get(0)}"/>
          </div>
          <div>
            <div id="title">${InfoArr.get(1)}</div>
            <dl>
              <dt>장르</dt>
              <dd>${InfoArr.get(2)}</dd>
              <dt>작가</dt>
              <dd>${InfoArr.get(3)}</dd>
            </dl>
            <button type="button" class="blue" onclick="location.href='cart.jsp'">장바구니 담기</button><br>
            <div class="like-button-div">
            <button type="button" class="red">좋아요 ♥</button>
          	</div>
          </div>
        </div>
        <div id="intro-content">
        <c:forEach items="${articles}" var="article" >
        	<img src="${article.getImg()}"/>
        </c:forEach>

        </div>
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
