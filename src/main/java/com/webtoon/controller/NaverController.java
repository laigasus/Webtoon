package com.webtoon.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webtoon.domain.ListVO;
import com.webtoon.domain.NaverVO;
import com.webtoon.external.CalculateDate;
import com.webtoon.service.NaverService;

@Controller
public class NaverController {

	@Autowired
	private NaverService service;
	
	//네비에 네이버를 눌렀을때 화면
	@GetMapping("/naver")
	public String naverListView(Model model, NaverVO vo) {
		System.out.println("네이버 ");
		//CalculateDate 사용법 아직모름
		ArrayList<NaverVO> naverList= service.listBoard(CalculateDate.calcDayOfWeek("kor", "월"));  ///listBoard 반환값 ArrayList<NaverVO>
		for(NaverVO naverVO : naverList ) {
			System.out.println("list.. " + naverVO);
		}
		model.addAttribute("naverList",naverList);
		
		return "naver";  //naver.jsp 출력
	}
	
	//  
	@GetMapping("/toon_list")
	public String toonListView(Model model,ListVO vo) {
		
		ArrayList<ListVO> toonList = service.toonList(URL, id);
		
		model.addAttribute("toonList",toonList);
		
		return "toon_list";
	}
	
	

	
}
