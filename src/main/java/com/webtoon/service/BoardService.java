package com.webtoon.service;

import java.util.List;

import com.webtoon.domain.BoardVO;

public interface BoardService {
	// 글 등록 메서드
	void regist(String email, String writer, String title, String content, String absoluteImgPath);

	// 글 목록을 가지고 오는 메서드
	List<BoardVO> listBoard(int startRow,int pageSize);

	// 내가 쓴 글 목록을 가지고 오는 메서드
	List<BoardVO> myListBoard(String email);

	// 글 상세보기 요청을 처리할 메서드
	BoardVO contentBoard(int bId);

	// 글 수정 요청을 처리할 메서드
	void updateBoard(String title, String content, String img, int bId);

	// 글 삭제 요청을 처리할 메서드
	void deleteBoard(int bId);

	// 글 검색 요청을 처리할 메서드
	List<BoardVO> searchBoard(String search, String category);

	// 관리자의 글을 가지고 오는 메서드
	List<BoardVO> AdminListBoard();

	// DB에 있는 view의 값을 증가 시켜주는 메서드
	void viewIncrease(int bId, int bView);
	
	// 글갯수를 받아온다.
	int getCountBoard();
}
