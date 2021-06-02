package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webtoon.domain.MyWebtoonVO;

public interface WebtoonMapper {
	// insertQuery(); 를 바꿈
	// int day, String title, String url, String thumb
	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon 활용
	@Insert("INSERT INTO webtoon (no, day, title, url, thumb) VALUES (0, #{day}, #{title}, #{url}, #{thumb}) ")
	public void insertWebtoon(@Param("day") int day, @Param("title") String title, @Param("url") String url,
			@Param("thumb") String thumb);

	// String id, String thumb, String title, String url, String day
	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon_list 활용
	@Insert("INSERT INTO webtoon_list (id, thumb, title, url, day) VALUES (#{id}, #{thumb}, #{title}, #{url}, #{day}) ")
	public void insertWebtoonList(@Param("id") String id, @Param("thumb") String thumb, @Param("title") String title,
			@Param("url") String url, @Param("day") String day);

	// String url, String img
	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon_view 활용
	@Insert("INSERT INTO webtoon_view (url, num, img) VALUES (#{url}, 0, #{img}) ")
	public void insertWebtoonView(@Param("url") String url, @Param("img") String img);

	// ????? 데이터를 도출하는 의도를 모르겠음
	// String webtoonTitle, String login
	// 해당 웹툰 좋아요를 확인하는 메서드. 좋아하면 1, 아니면 0
	@Select("select * from my_toon WHERE mt_title=#{webtoonTitle} AND mt_user=#{login}")
	public MyWebtoonVO myWebtoonCheck(@Param("webtoonTitle") String webtoonTitle, @Param("login") String login);

	// String imgSrc, String webtoonTitle, String webtoonUrl, String login
	// 사용자가 원하는 만화를 북마크하는 메서드(추가)
	@Insert("insert into my_toon values(#{imgSrc}, #{webtoonTitle}, #{webtoonUrl}, #{login})")
	public void myWebtoonUpload(@Param("imgSrc") String imgSrc, @Param("webtoonTitle") String webtoonTitle,
			@Param("webtoonUrl") String webtoonUrl, @Param("login") String login);

	// String webtoonTitle, String login
	// 사용자가 원하는 만화를 북마크하는 메서드(삭제)
	@Delete("delete from my_toon where mt_title=#{webtoonTitle} AND mt_user=#{login}")
	public void myWebtoonDelete(@Param("webtoonTitle") String webtoonTitle, @Param("login") String login);
	// mt_title이랑 mt_user 로구분해서 삭제

	// String title
	// 크롤링한 데이터를 처리하는데 필요한 메서드(선택). webtoon 테이블 select문
	@Select("SELECT count(*) as cnt FROM webtoon where title = #{title}")
	public int selectQuery(@Param("title") String title);

	// String tableName
	// 크롤링한 데이터를 처리하는데 필요한 메서드(삭제). webtoon 테이블 delete문
	@Delete("TRUNCATE TABLE ${tableName} ")
	public void deleteQuery(@Param("tableName") String tableName);

//	// 원래 없어서 추가했음
//	// 웹에서 만화를 크롤링하는 메서드. 만화 목록
//	public void webtoonCrawling();
//
//	// String URL, String id
//	// 웹에서 만화를 크롤링하는 메서드. 만화 목차
//	public void webtoonListCrawling(String URL, String id);
//
//	// String URL
//	// 웹에서 만화를 크롤링하는 메서드. 만화 뷰
//	public void webtoonViewCrawling(String URL);
//
//	// String URL
//	// 선택한 만화의 정보를 출력하는 메서드
//	public ArrayList<String> webtoonInfoCrawling(String URL);

	// String email
	// 선택한 만화의 목차를 출력하는 메서드
	@Select("select * from my_toon where mt_user = #{email}")
	public ArrayList<MyWebtoonVO> getMyWebtoonList(@Param("email") String email);
}
