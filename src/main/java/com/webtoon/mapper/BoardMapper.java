package com.webtoon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webtoon.domain.BoardVO;

public interface BoardMapper {
	// 글 등록 메서드
	@Select("insert into toon_board values(null,?,?,?,?,?,?,?)")
	void regist(String email, String writer, String title, String content, String absoluteImgPath);

	// 글 목록을 가지고 오는 메서드
	@Select("SELECT * FROM toon_board WHERE bd_email !='root@naver.com' ORDER BY bd_num DESC LIMIT ?,?;")
	List<BoardVO> listBoard(int startRow,int pageSize);

	// 내가 쓴 글 목록을 가지고 오는 메서드
	@Select("select * from toon_board where bd_email ='".concat(email) + "' order by bd_num DESC;")
	List<BoardVO> myListBoard(String email);

	// 글 상세보기 요청을 처리할 메서드
	@Select("SELECT * FROM toon_board WHERE bd_num=?")
	BoardVO contentBoard(int bId);

	// 글 수정 요청을 처리할 메서드
	@Update("UPDATE toon_board SET " + "bd_title=?, bd_content=?,bd_img=? " + "WHERE bd_num=?")
	void updateBoard(String title, String content, String img, int bId);

	// 글 삭제 요청을 처리할 메서드
	@Delete("DELETE FROM toon_board WHERE bd_num=?")
	void deleteBoard(int bId);

	// 글 검색 요청을 처리할 메서드
	@Select("SELECT * FROM toon_board WHERE bd_title LIKE ? AND bd_email !='root@naver.com'")
	List<BoardVO> searchBoard(String search, String category);

	// 관리자의 글을 가지고 오는 메서드
	@Select("SELECT * FROM toon_board WHERE bd_email='root@naver.com' ORDER BY bd_num DESC")
	List<BoardVO> AdminListBoard();

	// DB에 있는 view의 값을 증가 시켜주는 메서드
	@Update("UPDATE toon_board SET " + "bd_view=? " + "WHERE bd_num=?")
	void viewIncrease(int bId, int bView);
	
	// 글갯수를 받아온다.
	@Select("select count(*) from toon_board")
	int getCountBoard();
}
