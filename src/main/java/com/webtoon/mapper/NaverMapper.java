package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonViewVO;

public interface NaverMapper {
	// int YoIll
	// 네이버 웹툰 요일별 목록을 가져와 웹화면에 출력(naver.jsp)하기 위한 메소드
	@Select("SELECT * FROM webtoon where day=#{YoIll} ORDER BY no ASC")
	public ArrayList<WebtoonVO> listBoard(int YoIll);

	// String searchParam
	// 네이버 웹툰 검색 결과를 출력하는 메서드
	@Select("SELECT * FROM webtoon where title LIKE concat('%',#{searchParam},'%') ORDER BY no ASC")
	public ArrayList<WebtoonVO> searchBoard(String searchParam);

	// String URL, String id 이건 매개변수 2개 받는데 하나를 쓰넹 뭐지..;;;;
	// 선택한 만화의 에피소드 목록들을 출력하는 메서드
	@Select("SELECT * FROM webtoon_list where id=#{id} ORDER BY num ASC")
	public ArrayList<WebtoonListVO> toonList(String URL, String id);

	// String url
	// 해당 에피소드의 이미지들을 만화부 형식으로 출력하는 메서드
	@Select("SELECT * FROM webtoon_view where url=#{url} ORDER BY num ASC")
	public ArrayList<WebtoonViewVO> toonView(String url);

//	// 선택한 만화의 정보를 산출하는 메서드
//	// SQL문이 없어!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	public ArrayList<String> toonInfo(String URL);
}
