$(document).ready(function(){
	var userBarOpened = false;		// 회원 바가 열린 상태인가?
	var searchBarOpened = false;	// 검색 바가 열린 상태인가?

	$("#user").click(function(){	// 회원 버튼을 누렀을 때
		if(searchBarOpened == true){	// 검색 바가 이미 열린 상태면 먼저 감추고
			$("#search-bar, #search-close").css("display", "none");
			$("#search-img").fadeIn(200);
			searchBarOpened = false;
			$("#search").toggleClass("opened");
		}
		if(userBarOpened == true){	// 회원 바가 이미 열린 상태면
			$("#user-bar, #user-close").css("display", "none");	// 회원 바와 닫기 버튼을 감추고
			$("#user-img").fadeIn(200);	// 원래 회원 버튼을 보이게
			userBarOpened = false;
		}else{	// 열려있지 않은 상태면
			$("#user-img").fadeOut(0);	// 원래 회원 버튼을 감추고
			$("#user-bar, #user-close").fadeIn(200);	// 회원 바와 닫기 버튼을 보이게
			userBarOpened=true;
		}
		$(this).toggleClass("opened");
	});

	$("#search").click(function(){	// 검색 버튼을 눌렀을 때
		if(userBarOpened == true){	// 회원 바가 이미 열린 상태면 먼저 감추고
			$("#user-bar, #user-close").css("display", "none");
			$("#user-img").fadeIn(200);
			userBarOpened = false;
			$("#user").toggleClass("opened");
		}
		if(searchBarOpened == true){	// 이미 열린 상태면
			$("#search-bar, #search-close").css("display", "none");	// 검색 바와 닫기 버튼을 감추고
			$("#search-img").fadeIn(200);	// 원래 검색 버튼을 보이게
			searchBarOpened = false;
		}else{	// 열려있지 않은 상태면
			$("#search-img").fadeOut(0);	// 원래 검색 버튼을 감추고
			$("#search-bar, #search-close").fadeIn(200);	// 검색 바와 닫기 버튼을 보이게
			searchBarOpened = true;
		}
		$(this).toggleClass("opened");
	});


	var currentSlide = 1;	// 현재 슬라이드

	$(".left").click(function(){	// 슬라이더 왼쯕 버튼을 누르면
		$("#slide-" + currentSlide).css("display", "none");
		if((currentSlide == 1)) currentSlide = 5;	// 첫 슬라이드인 경우, 현재 슬라이드 - 1이 4가 되도록 현재 슬라이드를 5로 설정
		$("#slide-" + (currentSlide - 1)).css({opacity: 0, display: 'flex'}).animate({	// 현재 슬라이드 - 1로 이동, 0.5초간 투명도를 0에서 1로
			opacity: 1
		}, 500);
		currentSlide--;
	});

	$(".right").click(function(){	// 슬라이더 오른쪽 버튼을 누르면
		$("#slide-" + currentSlide).css("display", "none");
		if((currentSlide == 4)) currentSlide = 0;	// 마지막 슬라이드인 경우, 현재 슬라이드 + 1이 1이 되도록 현재 슬라이드를 0으로 설정
		$("#slide-" + (currentSlide + 1)).css({opacity: 0, display: 'flex'}).animate({	// 현재 슬라이드 + 1로 이동, 0.5초간 투명도를 0에서 1로
			opacity: 1
		}, 500);
		currentSlide++;
	});


	$("#user")		// 네비게이션 회원 버튼 마우스 오버 효과
	.mouseover(function(){	// 마우스를 오버할 때
		$("#user-img").attr("src", "img/button/nav/user-hover.svg");
	})
	.mouseout(function(){	// 마우스가 벗어났을 때
		$("#user-img").attr("src", "img/button/nav/user.svg");
	});

	$("#search")	// 네비게이션 검색 버튼 마우스 오버 효과
	.mouseover(function(){	// 마우스를 오버할 때
		$("#search-img").attr("src", "img/button/nav/search-hover.svg");
	})
	.mouseout(function(){	// 마우스가 벗어났을 때
		$("#search-img").attr("src", "img/button/nav/search.svg");
	});
});
