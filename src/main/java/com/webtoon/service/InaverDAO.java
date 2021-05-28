package com.webtoon.service;

import java.util.ArrayList;

public interface InaverDAO {

	// 네이버 웹툰 요일별 목록을 가져와 웹화면에 출력(naver.jsp)하기 위한 메소드
	ArrayList<naverVO> listBoard(int YoIll);

	// 네이버 웹툰 검색 결과를 출력하는 메서드
	ArrayList<naverVO> searchBoard(String searchParam);

	// 선택한 만화의 정보를 산출하는 메서드
	ArrayList<String> toonInfo(String URL);

	// 선택한 만화의 에피소드 목록들을 출력하는 메서드
	ArrayList<listVO> toonList(String URL, String id);

	// 해당 에피소드의 이미지들을 만화부 형식으로 출력하는 메서드
	ArrayList<viewVO> toonView(String url);
}
