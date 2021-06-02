package com.webtoon.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.BoardVO;
import com.webtoon.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	////////////////////////////////////////////////////////////////

	// 글등록 메서드
	@Override
	public void regist(String email, String writer, String title, String content, String absoluteImgPath) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		boardMapper.regist(title, writer, time, 1, content, absoluteImgPath, email);
	}

	@Override
	public ArrayList<BoardVO> listBoard(int startRow, int pageSize) {
		return boardMapper.listBoard(startRow, pageSize);
	}

	@Override
	public ArrayList<BoardVO> myListBoard(String email) {
		return boardMapper.myListBoard(email);
	}

	@Override
	public BoardVO contentBoard(int bId) {
		return boardMapper.contentBoard(bId);
	}

	@Override
	public void updateBoard(String bd_title, String bd_content, String absoluteImgPath, int bId) {
		boardMapper.updateBoard(bd_title, bd_content, absoluteImgPath, bId);
	}

	@Override
	public void deleteBoard(int bId) {
		boardMapper.deleteBoard(bId);
	}

	@Override
	public ArrayList<BoardVO> searchBoard(String search, String category) {
		return boardMapper.searchBoard(search, category);
	}

	@Override
	public void viewIncrease(int bId, int bView) {
		boardMapper.viewIncrease(bId, bView);
	}

	@Override
	public ArrayList<BoardVO> AdminListBoard() {
		return boardMapper.AdminListBoard();
	}

	@Override
	public int getCountBoard() {
		return boardMapper.getCountBoard();
	}

}
