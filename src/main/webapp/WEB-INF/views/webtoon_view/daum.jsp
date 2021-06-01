<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>다음 웹툰 - 툰스토어</title>
    <meta charset="utf-8" />
  </head>
  <body>
    <jsp:include page="../etc/nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <header>
      <div class="container">다음<br>웹툰<img src="img/menu/site.svg"/></div>
    </header>
    <section>
      <div class="container">
        <h1><img src="img/button/arrow.svg" /> 다음 웹툰</h1>
        <ul id="day-tab">
          <li><a>월</a><img src="img/day/mon.svg" /></li>
          <li class="active"><a>화</a><img src="img/day/tue.svg" /></li>
          <li><a>수</a><img src="img/day/wed.svg" /></li>
          <li><a>목</a><img src="img/day/thu.svg" /></li>
          <li><a>금</a><img src="img/day/fri.svg" /></li>
          <li><a>토</a><img src="img/day/sat.svg" /></li>
          <li><a>일</a><img src="img/day/sun.svg" /></li>
        </ul>
        <div class="list-container">
          <a href="detail.jsp">
            <img src="img/toons/SuicideCat.jpg" />
            <ul>
              <li class="title">자살캣<span class="badge">UP</span></li>
              <li class="genre">학원</li>
              <li class="author">가위</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/thewizard.jpg" />
            <ul>
              <li class="title">백도사<span class="badge">UP</span></li>
              <li class="genre">판타지 / 액션</li>
              <li class="author">늴릴 / 이끼</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/ccromance.jpg" />
            <ul>
              <li class="title">청춘로맨스<span class="badge">UP</span></li>
              <li class="genre">로맨스</li>
              <li class="author">BV / 미울</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/bananatoon.jpg" />
            <ul>
              <li class="title">바나나툰<span class="badge">UP</span></li>
              <li class="genre">드라마 / 일상</li>
              <li class="author">연양갱</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/lovebarometer.jpg" />
            <ul>
              <li class="title">순정 바로미터<span class="badge">UP</span></li>
              <li class="genre">로맨스 / 학원</li>
              <li class="author">산사</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/woori.jpg" />
            <ul>
              <li class="title">우리사이느은</li>
              <li class="genre">로맨스</li>
              <li class="author">이연지</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/unknown_code.jpg" />
            <ul>
              <li class="title">언노운 코드<span class="badge">UP</span></li>
              <li class="genre">SF / 미스터리</li>
              <li class="author">김칸비 / 후파</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/nadu.jpg" />
            <ul>
              <li class="title">
                낭만두더지 나두<span class="badge">UP</span>
              </li>
              <li class="genre">로맨스</li>
              <li class="author">크레이지버드 스튜디오</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/mycatsir.jpg" />
            <ul>
              <li class="title">내 고양이! 님</li>
              <li class="genre">일상 / 개그</li>
              <li class="author">홍끼</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/do_family.jpg" />
            <ul>
              <li class="title">도령의 가족<span class="badge">UP</span></li>
              <li class="genre">드라마 / 로맨스</li>
              <li class="author">미울 / BV</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/double_casting.jpg" />
            <ul>
              <li class="title">더블캐스팅</li>
              <li class="genre">액션 / 미스터리</li>
              <li class="author">신영우</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/horns.jpg" />
            <ul>
              <li class="title">뿔뿔뿔뿔</li>
              <li class="genre">로맨스 / 드라마</li>
              <li class="author">나지</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/neo_magnet.jpg" />
            <ul>
              <li class="title">네오 마그넷<span class="badge">UP</span></li>
              <li class="genre">판타지 / 액션</li>
              <li class="author">HOOPA</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/m_street_no9.jpg" />
            <ul>
              <li class="title">마들레느가 9번지</li>
              <li class="genre">음식 / 로맨스</li>
              <li class="author">진혜리</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="img/toons/white_epic.jpg" />
            <ul>
              <li class="title">백서사시<span class="badge">UP</span></li>
              <li class="genre">액션 / 판타지</li>
              <li class="author">늴릴 / GMAN</li>
            </ul>
          </a>
        </div>
      </div>
    </section>
    <jsp:include page="../etc/footer.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <a href="#nav-container" id="top"><img src="img/button/top.svg" /></a>
    <script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>
