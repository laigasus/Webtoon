<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>자주 묻는 질문 - 툰스토어</title>
  </head>
  <body>
    <jsp:include page="nav.jsp">
      <jsp:param name="name" value="value" />
    </jsp:include>
    <header>
      <div class="container">
        자주 묻는 질문
        <img src="img/menu/faq.svg" />
      </div>
    </header>
    <section>
      <div class="container" id="faq">
        <h1><img src="img/button/arrow.svg" /> 자주 묻는 질문</h1>
        <div>
          <h2>TOON store란?</h2>
          <p>
            TOON store는 일반도서, 만화, 장르소설, 영화 및 방송 동영상, 모바일용
            앱 콘텐츠를<br />온라인으로 결제하여, 다운로드, 스트리밍, 구매 등의
            방법을 통해 제공하는 서비스입니다.
          </p>
          <p>
            유료 콘텐츠는 TOON store 포인트 또는 카드/계좌 간편결제 및 휴대폰
            계좌이체등을 통해 대여 또는 구매하여 이용할 수 있습니다.
          </p>
          <p>
            모바일 TOON store를 이용하시는 경우,<br />콘텐츠에 따라 TOON store
            TV 앱, TOON store 북스 앱, TOON store 뮤직 앱과 같은 전용
            어플리케이션 추가가 필요할 수 있습니다.
          </p>
          <p>
            PC TOON store를 이용하시는 경우에는 북스 뷰어, TOON store 미디어
            플레이어를 설치하시면<br />보다 편리하게 콘텐츠를 이용하실 수
            있습니다.
          </p>
        </div>
        <div>
          <h2>e북, 만화, 장르소설 이용요금</h2>
          <p>
            제휴 출판사의 요청에 따라 구매/대여 시 콘텐츠별로 가격과 이용 기간이
            다릅니다.<br>자유이용권의 가격과 이용기간은 다음과 같습니다.
          </p>
          <p><strong>[자유이용권 가격/이용기간]</strong></p>
          <p>
            - 만화: 2,000원(1일) / 4,000원(7일) / 9,000원(30일)<br />- 장르소설:
            3,000원(1일) / 10,000원(7일) / 30,000원(30일)
          </p>
          <p>
            자유이용권 전용 도서는 도서 상세 정보 페이지에 “자유이용권으로 이용
            가능”이라고 쓰여 있습니다.
          </p>
        </div>
        <div>
          <h2>TOON store에 제안합니다.</h2>
          <p>TOON store 담당자에게 제안하실 의견이 있다면 접수해 주세요.</p>
          <p><strong>제안사항/의견 접수 전용 채널으로, 별도의 결과를 안내해 드리지
              않고 있습니다.</strong><br>개별 답변을 원하시는 그 외 문의는 연관 도움말을 통해 접수하여
            주세요.<br>보내주신 의견은 모두 서비스 개선을 위해 소중히 활용되고
            있습니다.</p>
          <div id="feedback">
            <textarea
              rows="6"
              name="contents"
              placeholder="여러분의 소중한 생각이 우리에게 큰힘이 됩니다."
            ></textarea>
            <button class="blue" type="submit">작성 완료</button>
          </div>
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
