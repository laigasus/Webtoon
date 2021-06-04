package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonViewVO;

public interface NaverMapper {

	// 네이버 웹툰 요일별 목록을 가져와 웹화면에 출력(naver.jsp)하기 위한 메소드
	@Select("SELECT * FROM webtoon where day=#{YoIll} ORDER BY no ASC")
	public ArrayList<WebtoonVO> listBoard(@Param("YoIll") int YoIll);

	// 네이버 웹툰 검색 결과를 출력하는 메서드
	@Select("SELECT * FROM webtoon where title LIKE concat('%',#{searchParam},'%') ORDER BY no ASC")
	public ArrayList<WebtoonVO> searchBoard(@Param("searchParam") String searchParam);

	// 선택한 만화의 에피소드 목록들을 출력하는 메서드
	@Select("SELECT * FROM webtoon_list where id=#{id} ORDER BY num ASC")
	public ArrayList<WebtoonListVO> toonList(@Param("URL") String URL, @Param("id") String id);

	// 해당 에피소드의 이미지들을 만화부 형식으로 출력하는 메서드
	@Select("SELECT * FROM webtoon_view where url=#{url} ORDER BY num ASC")
	public ArrayList<WebtoonViewVO> toonView(@Param("url") String url);
}
