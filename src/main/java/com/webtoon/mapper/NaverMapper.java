package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.webtoon.domain.listVO;
import com.webtoon.domain.naverVO;
import com.webtoon.domain.viewVO;

public interface NaverMapper {
	// 네이버 웹툰 요일별 목록을 가져와 웹화면에 출력(naver.jsp)하기 위한 메소드
	@Select("SELECT * FROM webtoon where day=? ORDER BY no ASC")
	ArrayList<naverVO> listBoard(int YoIll);

	// 네이버 웹툰 검색 결과를 출력하는 메서드
	@Select("SELECT * FROM webtoon where title LIKE " + "'%" + searchParam + "%'" + " ORDER BY no ASC")
	ArrayList<naverVO> searchBoard(String searchParam);

	// 선택한 만화의 에피소드 목록들을 출력하는 메서드
	@Select("SELECT * FROM webtoon_list where id=? ORDER BY num ASC")
	ArrayList<listVO> toonList(String URL, String id);

	// 해당 에피소드의 이미지들을 만화부 형식으로 출력하는 메서드
	@Select("SELECT * FROM webtoon_view where url=? ORDER BY num ASC")
	ArrayList<viewVO> toonView(String url);

	// 선택한 만화의 정보를 산출하는 메서드
	// SQL문이 없어!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	ArrayList<String> toonInfo(String URL);

}
