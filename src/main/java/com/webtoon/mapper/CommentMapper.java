package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webtoon.domain.CommentVO;

public interface CommentMapper {
	// 댓글 등록 메서드
	@Insert("INSERT INTO board_comment " + "(bd_num, cm_writer, cm_content, cm_like, email) "
			+ "VALUES (#{bd_num}, #{cm_writer}, #{cm_content}, 0, #{email})")
	void regist(int bd_num, String cm_writer, String cm_content, String email);

	// 댓글 목록을 가지고 오는 메서드
	@Select("SELECT * FROM board_comment WHERE bd_num=#{bd_num}")
	ArrayList<CommentVO> listComment(int bd_num);

	// 글 수정 요청을 처리할 메서드
	@Update("UPDATE board_comment SET " + "cm_content=#{cm_content} " + "WHERE bd_num=#{bd_num} AND cm_id=#{cm_id}")
	void updateComment(String cm_writer, String cm_content, int bd_num, int cm_id);

	// 댓글 삭제 요청을 처리할 메서드
	@Delete("DELETE FROM board_comment WHERE bd_num=#{bd_num} AND cm_id=#{cm_id}")
	void deleteComment(int bd_num, int cm_id);

	// 댓글 중 좋아요 수를 가장 많이 받은 글 정보를 처리할 메서드
	@Select("SELECT * FROM board_comment WHERE bd_num=#{bd_num} ORDER BY cm_like desc LIMIT 1")
	CommentVO bestComment(int bd_num);

	// 댓글에 좋아요를 추가하는 메서드
	@Update("UPDATE board_comment SET cm_like=cm_like+1 WHERE bd_num=#{bd_num} AND cm_id=#{cm_id}")
	void likeComment(int bd_num, int cm_id);
}
