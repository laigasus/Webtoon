<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
        커뮤니티
        <img src="img/menu/jajak.svg" />
      </div>
    </header>
    <section id="jajak-section">
      <div class="container">
        <h1><img src="img/button/arrow.svg" /> 커뮤니티</h1>
        <ul class="tabs" id="jajak-tab">
          <form>
            <input type="text" placeholder="검색어 입력" />
            <button type="submit">검색</button>
          </form>
        </ul>
        <div class="community-container" style="flex-direction: row-reverse">
          <div>db에서 사용자 작성 글 가져와서 여기에 추가</div>
          <div>db</div>
          <div>db</div>
        </div>
        <a href="jajak-upload.jsp"
          ><button class="blue">게시글 올리기</button></a
        >
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
