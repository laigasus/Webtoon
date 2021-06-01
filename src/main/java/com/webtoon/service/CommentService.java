package com.webtoon.service;

import java.util.ArrayList;

import com.webtoon.domain.CommentVO;

public interface CommentService {
	// 댓글 등록 메서드
	void regist(int bd_num, String cm_writer, String cm_content, String email);

	// 댓글 목록을 가지고 오는 메서드
	ArrayList<CommentVO> listComment(int bd_num);

	// 글 수정 요청을 처리할 메서드
	void updateComment(String cm_writer, String cm_content, int bd_num, int cm_id);

	// 댓글 삭제 요청을 처리할 메서드
	void deleteComment(int bd_num, int cm_id);

	// 댓글 중 좋아요 수를 가장 많이 받은 글 정보를 처리할 메서드
	CommentVO bestComment(int bd_num);

	// 댓글에 좋아요를 추가하는 메서드
	void likeComment(int bd_num, int cm_id);
}
