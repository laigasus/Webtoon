package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.webtoon.domain.MyWebtoonVO;

public interface WebtoonMapper {

	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon 활용
	@Insert("INSERT INTO webtoon (no, day, title, url, thumb) VALUES (0, #{day}, #{title}, #{url}, #{thumb}) ")
	void insertWebtoon(@Param("day") int day, @Param("title") String title, @Param("url") String url,
			@Param("thumb") String thumb);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon_list 활용
	@Insert("INSERT INTO webtoon_list (id, thumb, title, url, day) VALUES (#{id}, #{thumb}, #{title}, #{url}, #{day}) ")
	void insertWebtoonList(@Param("id") String id, @Param("thumb") String thumb, @Param("title") String title,
			@Param("url") String url, @Param("day") String day);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(추가). webtoon 테이블 insert문. webtoon_view 활용
	@Insert("INSERT INTO webtoon_view (url, num, img) VALUES (#{url}, 0, #{img}) ")
	void insertWebtoonView(@Param("url") String url, @Param("img") String img);

	// 해당 웹툰 좋아요를 확인하는 메서드. 좋아하면 1, 아니면 0
	@Select("SELECT * FROM my_toon WHERE mt_title=#{webtoonTitle} AND mt_user=#{login}")
	MyWebtoonVO myWebtoonCheck(@Param("webtoonTitle") String webtoonTitle, @Param("login") String login);

	// 사용자가 원하는 만화를 북마크하는 메서드(추가)
	@Insert("INSERT INTO my_toon VALUES(#{webtoonTitle}, #{login}, #{imgSrc}, #{webtoonUrl})")
	void myWebtoonUpload(@Param("webtoonTitle") String webtoonTitle, @Param("login") String login,
			@Param("imgSrc") String imgSrc, @Param("webtoonUrl") String webtoonUrl);

	// 사용자가 원하는 만화를 북마크하는 메서드(삭제)
	@Delete("DELETE FROM my_toon WHERE mt_title=#{webtoonTitle} AND mt_user=#{login}")
	void myWebtoonDelete(@Param("webtoonTitle") String webtoonTitle, @Param("login") String login);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(선택). webtoon 테이블 select문
	@Select("SELECT COUNT(*) AS cnt FROM webtoon WHERE title = #{title}")
	int selectQuery(@Param("title") String title);

	// 크롤링한 데이터를 처리하는데 필요한 메서드(삭제). webtoon 테이블 delete문
	@Delete("TRUNCATE TABLE ${tableName} ")
	void deleteQuery(@Param("tableName") String tableName);

	// 선택한 만화의 목차를 출력하는 메서드
	@Select("SELECT * FROM my_toon WHERE mt_user=#{email}")
	ArrayList<MyWebtoonVO> getMyWebtoonList(@Param("email") String email);
}
