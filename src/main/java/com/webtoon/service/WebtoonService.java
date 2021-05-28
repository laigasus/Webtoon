package com.webtoon.service;

import java.util.ArrayList;

import com.webtoon.domain.my_webtoon;

public interface WebtoonService {
	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon 활용
	void insertQuery(int day, String title, String url, String thumb);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon_list 활용
	void insertQuery(String id, String thumb, String title, String url, String day);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon_view 활용
	void insertQuery(String url, String img);

	// 해당 웹툰 좋아요를 확인하는 메서드. 좋아하면 1, 아니면 0
	int myWebtoonCheck(String webtoonTitle, String login);

	// 사용자가 원하는 만화를 북마크하는 메서드(추가)
	void myWebtoonUpload(String imgSrc, String webtoonTitle, String webtoonUrl, String login);

	// 사용자가 원하는 만화를 북마크하는 메서드(삭제)
	void myWebtoonDelete(String webtoonTitle, String login); // mt_title 이랑 mt_user 로 구분해서 삭제

	// 크롤링한 데이터를 처리하는데 필요한 메서드(선택). webtoon 테이블 select문
	int selectQuery(String title);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(삭제). webtoon 테이블 delete문
	void deleteQuery(String tableName);

	// 웹에서 만화를 크롤링하는 메서드. 만화 목록
	void webtoonCrawling();

	// 웹에서 만화를 크롤링하는 메서드. 만화 목차
	void webtoonListCrawling(String URL, String id);

	// 웹에서 만화를 크롤링하는 메서드. 만화 뷰
	void webtoonViewCrawling(String URL);

	// 선택한 만화의 정보를 출력하는 메서드
	ArrayList<String> webtoonInfoCrawling(String URL);

	// 선택한 만화의 목차를 출력하는 메서드
	ArrayList<my_webtoon> getMyWebtoonList(String email);
}
