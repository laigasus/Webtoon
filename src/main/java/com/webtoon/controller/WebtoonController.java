package com.webtoon.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonViewVO;
import com.webtoon.service.NaverService;

import com.webtoon.external.*;

@Controller
public class WebtoonController {

	@Autowired
	private NaverService service;

	// daum.jsp
	// 만화 종류중 다음 웹툰을 보여줌. 크롤링 X. 별 다른건 없음
	@GetMapping("/daum")
	public String daumGET() {

		return "daum";
	}

	@PostMapping("/daum")
	public String daumPOST() {
		return "daum";
	}
	/////////////////////////////////////////////////

	// detail.jsp
	// 웹툰 에피소드 선택시 컷씬들을 볼 수 있는 페이지
	@GetMapping("/detail")
	public String detailGET(HttpServletRequest request, Model model) {
		String URL = (String) request.getParameter("URL");
		String NO= (String) request.getParameter("no");

		URL = "https://comic.naver.com" + URL+"&no="+NO;

		ArrayList<String> InfoArr = service.toonInfo(URL);
		ArrayList<WebtoonViewVO> articles = service.toonView(URL);

		model.addAttribute("InfoArr", InfoArr);
		model.addAttribute("articles", articles);

		return "detail";
	}

	@PostMapping("/detail")
	public String detailPOST() {
		return "detail";
	}
	/////////////////////////////////////////////////

	// nate.jsp
	// 만화 종류중 네이트 웹툰을 보여줌. 크롤링 X. 별 다른건 없음
	@GetMapping("/nate")
	public String nateGET() {

		return "nate";
	}

	@PostMapping("/nate")
	public String natePOST() {
		return "nate";
	}
	/////////////////////////////////////////////////

	// 웹툰을 검색했을때의 결과를 출력함
	// naver_search_result.jsp POST
	@GetMapping("/naver_search_result")
	public String naverSearchResultGET(HttpServletRequest request, Model model) {
		String searchParam = (String) request.getParameter("q");
		ArrayList<WebtoonVO> articles = service.searchBoard(searchParam);
		model.addAttribute("articles", articles);

		return "naver_search_result";
	}

	@PostMapping("/naver_search_result")
	public String naverSearchResultPOST() {
		return "naver_search_result";
	}
	/////////////////////////////////////////////////

	// naver.jsp
	// 웹툰 대분류로 네이버로 들갔을 때 네이버 웹툰 목록들을 요일별로 출력
	@GetMapping("/naver")
	public String naverGET(HttpServletRequest request, Model model) throws Exception {
		request.setCharacterEncoding("utf-8");
		String choosedDay = (String) request.getParameter("choosedDay");
		System.out.println("choosedDay: " + choosedDay);
		String[] weekArr = { "", "", "", "", "", "", "" };

		for (int i = 0; i < weekArr.length; i++) {
			if (choosedDay == null) {
				if (i == CalculateDate.dayOfWeek - 1) {
					choosedDay = CalculateDate.calcTodayYoill(i, "kor");
					weekArr[i] = "active";
					break;
				}
			} else {
				if (choosedDay.equals(CalculateDate.dayOfWeekKor[i])) {
					weekArr[i] = "active";
					break;
				}
			}
		}
		System.out.println("weekArr: " + weekArr);
		System.out.println("choosedDay: " + choosedDay);

		String[] dayOfWeekKor = CalculateDate.dayOfWeekKor;

		System.out.println("요일값: " + CalculateDate.calcDayOfWeek("kor", choosedDay));
		ArrayList<WebtoonVO> articles = service.listBoard(CalculateDate.calcDayOfWeek("kor", choosedDay));
		System.out.println("컨트롤러 실행!");
		model.addAttribute("dayOfWeekKor", dayOfWeekKor);
		model.addAttribute("choosedDay", choosedDay);
		model.addAttribute("weekArr", weekArr);
		model.addAttribute("articles", articles);

		return "naver";
	}

	@PostMapping("/naver")
	public String naverPOST() {
		return "naver";
	}
	/////////////////////////////////////////////////

	// toon_list.jsp
	// 네이버 만화를 골랐을때의 에피소드들과 해당 만화의 정보를 담고 있음
	@GetMapping("/toon_list")
	public String toonListGET(HttpServletRequest request, Model model, WebtoonListVO vo) {

		String URL = (String) request.getParameter("URL");
		URL = "https://comic.naver.com" + URL;
		System.out.println("url: " + URL);
		String titleId = URL.substring(49, 55);

		ArrayList<String> InfoArr = service.toonInfo(URL);
		ArrayList<WebtoonListVO> articles = service.toonList(URL, titleId);

		model.addAttribute("InfoArr", InfoArr);
		model.addAttribute("articles", articles);

		return "toon_list";
	}

	@PostMapping("/toon_list")
	public String toonListPOST() {
		return "toon_list";
	}
	/////////////////////////////////////////////////
}
