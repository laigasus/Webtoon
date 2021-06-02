<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>네이트 웹툰 - 툰스토어</title>
    <meta charset="UTF-8" />
  </head>
  <body>
    <jsp:include page="nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>

    <header>
      <div class="container">
        네이트<br />웹툰
        <img src="${path}/resources/img/menu/site.svg" />
      </div>
    </header>

    <section>
      <div class="container">
        <h1><img src="${path}/resources/img/button/arrow.svg" /> 네이트 웹툰</h1>
        <ul id="day-tab">
          <li><a>월</a><img src="${path}/resources/img/day/mon.svg" /></li>
          <li class="active"><a>화</a><img src="${path}/resources/img/day/tue.svg" /></li>
          <li><a>수</a><img src="${path}/resources/img/day/wed.svg" /></li>
          <li><a>목</a><img src="${path}/resources/img/day/thu.svg" /></li>
          <li><a>금</a><img src="${path}/resources/img/day/fri.svg" /></li>
          <li><a>토</a><img src="${path}/resources/img/day/sat.svg" /></li>
          <li><a>일</a><img src="${path}/resources/img/day/sun.svg" /></li>
        </ul>
        <div class="list-container" style="flex-direction: row-reverse">
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/double_casting.jpg" />
            <ul>
              <li class="title">더블캐스팅</li>
              <li class="genre">액션 / 미스터리</li>
              <li class="author">신영우</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/horns.jpg" />
            <ul>
              <li class="title">뿔뿔뿔뿔</li>
              <li class="genre">로맨스 / 드라마</li>
              <li class="author">나지</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/neo_magnet.jpg" />
            <ul>
              <li class="title">네오 마그넷<span class="badge">UP</span></li>
              <li class="genre">판타지 / 액션</li>
              <li class="author">HOOPA</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/m_street_no9.jpg" />
            <ul>
              <li class="title">마들레느가 9번지</li>
              <li class="genre">음식 / 로맨스</li>
              <li class="author">진혜리</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/white_epic.jpg" />
            <ul>
              <li class="title">백서사시<span class="badge">UP</span></li>
              <li class="genre">액션 / 판타지</li>
              <li class="author">늴릴 / GMAN</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/revatoon.jpg" />
            <ul>
              <li class="title">레바툰<span class="badge">UP</span></li>
              <li class="genre">개그 / 일상</li>
              <li class="author">레바</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/shingi.jpg" />
            <ul>
              <li class="title">신기록</li>
              <li class="genre">시대극 / 판타지</li>
              <li class="author">리율</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/cartoon_hero.jpg" />
            <ul>
              <li class="title">4컷용사</li>
              <li class="genre">판타지 / 개그</li>
              <li class="author">고지라군</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/gowrong.jpg" />
            <ul>
              <li class="title">삐뚤어질테다<span class="badge">UP</span></li>
              <li class="genre">학원 / 개그</li>
              <li class="author">행자</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/art_of_loving.jpg" />
            <ul>
              <li class="title">사랑의 기술<span class="badge">UP</span></li>
              <li class="genre">로맨스</li>
              <li class="author">손휘현 / 이윤지</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/SuicideCat.jpg" />
            <ul>
              <li class="title">자살캣</li>
              <li class="genre">학원</li>
              <li class="author">가위</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/thewizard.jpg" />
            <ul>
              <li class="title">백도사</li>
              <li class="genre">판타지 / 액션</li>
              <li class="author">늴릴 / 이끼</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/ccromance.jpg" />
            <ul>
              <li class="title">청춘로맨스<span class="badge">UP</span></li>
              <li class="genre">로맨스</li>
              <li class="author">BV / 미울</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/bananatoon.jpg" />
            <ul>
              <li class="title">바나나툰</li>
              <li class="genre">드라마 / 일상</li>
              <li class="author">연양갱</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/lovebarometer.jpg" />
            <ul>
              <li class="title">순정 바로미터</li>
              <li class="genre">로맨스 / 학원</li>
              <li class="author">산사</li>
            </ul>
          </a>
        </div>
      </div>
    </section>
    <jsp:include page="footer.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <a href="#nav-container" id="top"><img src="${path}/resources/img/button/top.svg" /></a>
    <script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
    <script src="${path}/resources/js/script.js"></script>
  </body>
</html>
