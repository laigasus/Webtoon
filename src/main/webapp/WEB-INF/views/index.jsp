<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>툰스토어</title>
    <meta charset="UTF-8" />
  </head>
  <body>
    <jsp:include page="nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <header>
      <div class="container">오늘의<br>웹툰<img src="${path}/resources/img/menu/today.svg"/></div>
    </header>
    <section>
      <div class="container">
        <div id="slide-1">
          <button class="left"><img src="${path}/resources/img/button/slider/back.svg"/></button>
          <img src="${path}/resources/img/slides/slide-1.png" />
          <button class="right">
            <img src="${path}/resources/img/button/slider/next.svg" />
          </button>
        </div>
        <div id="slide-2">
          <button class="left"><img src="${path}/resources/img/button/slider/back.svg"/></button>
          <img src="${path}/resources/img/slides/slide-2.jpg" />
          <button class="right">
            <img src="${path}/resources/img/button/slider/next.svg" />
          </button>
        </div>
        <div id="slide-3">
          <button class="left"><img src="${path}/resources/img/button/slider/back.svg"/></button>
          <img src="${path}/resources/img/slides/slide-3.png" />
          <button class="right">
            <img src="${path}/resources/img/button/slider/next.svg" />
          </button>
        </div>
        <div id="slide-4">
          <button class="left"><img src="${path}/resources/img/button/slider/back.svg" /></button>
          <img src="${path}/resources/img/slides/slide-4.png" />
          <button class="right">
            <img src="${path}/resources/img/button/slider/next.svg" />
          </button>
        </div>
        <h1><img src="${path}/resources/img/button/arrow.svg" /> 오늘 랭킹 TOP 10</h1>
        <div class="list-container">
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
              <li class="title">신기록<span class="badge">UP</span></li>
              <li class="genre">시대극/판타지</li>
              <li class="author">리율</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/cartoon_hero.jpg" />
            <ul>
              <li class="title">4컷용사<span class="badge">UP</span></li>
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
              <li class="title">자살캣<span class="badge">UP</span></li>
              <li class="genre">학원</li>
              <li class="author">가위</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/thewizard.jpg" />
            <ul>
              <li class="title">백도사<span class="badge">UP</span></li>
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
              <li class="title">바나나툰<span class="badge">UP</span></li>
              <li class="genre">드라마 / 일상</li>
              <li class="author">연양갱</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/lovebarometer.jpg" />
            <ul>
              <li class="title">순정 바로미터<span class="badge">UP</span></li>
              <li class="genre">로맨스 / 학원</li>
              <li class="author">산사</li>
            </ul>
          </a>
        </div>

        <h1><img src="${path}/resources/img/button/arrow.svg" /> 웹툰 기다리면 무료</h1>
        <div class="list-container">
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/woori.jpg" />
            <ul>
              <li class="title">우리사이느은</li>
              <li class="genre">로맨스</li>
              <li class="author">이연지</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/unknown_code.jpg" />
            <ul>
              <li class="title">언노운 코드<span class="badge">UP</span></li>
              <li class="genre">SF / 미스터리</li>
              <li class="author">김칸비 / 후파</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/nadu.jpg" />
            <ul>
              <li class="title">
                낭만두더지 나두<span class="badge">UP</span>
              </li>
              <li class="genre">로맨스</li>
              <li class="author">크레이지버드 스튜디오</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/mycatsir.jpg" />
            <ul>
              <li class="title">내 고양이! 님</li>
              <li class="genre">일상 / 개그</li>
              <li class="author">홍끼</li>
            </ul>
          </a>
          <a href="detail.jsp">
            <img src="${path}/resources/img/toons/do_family.jpg" />
            <ul>
              <li class="title">도령의 가족<span class="badge">UP</span></li>
              <li class="genre">드라마 / 로맨스</li>
              <li class="author">미울 / BV</li>
            </ul>
          </a>
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
