package com.webtoon.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webtoon.domain.BoardVO;

public interface BoardMapper {
	// 글 등록 메서드
	@Select("insert into toon_board values(null, #{bd_title}, #{bd_writer},#{bd_date},#{bd_view},#{bd_content},#{bd_img},#{bd_email})")
	public void regist(@Param("title") String title,@Param("writer") String writer,@Param("time") Timestamp time,@Param("view") int view,@Param("content") String content,@Param("absoluteImgPath") String absoluteImgPath,
			@Param("email") String email);

	// 글 목록을 가지고 오는 메서드
	// 원래 Sql "SELECT * FROM toon_board WHERE bd_email !='root@naver.com' ORDER BY
	// bd_num DESC LIMIT ?,?;
	@Select("SELECT * FROM toon_board WHERE bd_email !='root@naver.com' ORDER BY bd_num DESC LIMIT ${startRow},${pageSize};")
	ArrayList<BoardVO> listBoard(@Param("startRow") int startRow,@Param("pageSize") int pageSize);

	// 내가 쓴 글 목록을 가지고 오는 메서드
	// 원래 sql문 "select * from toon_board where bd_email ='".concat(email) + "' order
	// by bd_num DESC;"
	@Select("select * from toon_board where bd_email ='.concat(#{bd_email}) + ' order by bd_num DESC;")
	ArrayList<BoardVO> myListBoard(@Param("email") String email);

	// 글 상세보기 요청을 처리할 메서드
	@Select("SELECT * FROM toon_board WHERE bd_num=#{bd_num}")
	BoardVO contentBoard(@Param("bId") int bId);

	// 글 수정 요청을 처리할 메서드
	@Update("UPDATE toon_board SET " + "bd_title=#{bd_title}, bd_content=#{bd_content},bd_img=#{bd_img} "
			+ "WHERE bd_num=#{bd_num}")
	void updateBoard(@Param("bd_title") String bd_title,@Param("bd_content") String bd_content,@Param("absoluteImgPath") String absoluteImgPath,@Param("bId") int bId);

	// 글 삭제 요청을 처리할 메서드
	@Delete("DELETE FROM toon_board WHERE bd_num=#{bd_num}")
	void deleteBoard(@Param("bId") int bId);

	// 글 검색 요청을 처리할 메서드
	// SELECT * FROM toon_board WHERE bd_title LIKE ? AND bd_email
	// !='root@naver.com'
	//
	@Select("SELECT * FROM toon_board WHERE ${category} LIKE ${search} AND bd_email !='root@naver.com'")
	ArrayList<BoardVO> searchBoard(@Param("search") String search,@Param("category") String category);

	// 관리자의 글을 가지고 오는 메서드
	@Select("SELECT * FROM toon_board WHERE bd_email='root@naver.com' ORDER BY bd_num DESC")
	ArrayList<BoardVO> AdminListBoard();

	// DB에 있는 view의 값을 증가 시켜주는 메서드
	@Update("UPDATE toon_board SET bd_view=${bView}+1 WHERE bd_num=${bId}")
	void viewIncrease(@Param("bId") int bId,@Param("bView") int bView);

	// 글갯수를 받아온다.
	@Select("select count(*) from toon_board")
	int getCountBoard();
}
