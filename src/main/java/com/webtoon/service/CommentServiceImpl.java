package com.webtoon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.CommentVO;
import com.webtoon.mapper.CommentMapper;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	////////////////////////////////////////////////////////////////

	@Override
	public void regist(int bd_num, String cm_writer, String cm_content, String email) {
		commentMapper.regist(bd_num, cm_writer, cm_content, email);
	}

	@Override
	public List<CommentVO> listComment(int bd_num) {
		return commentMapper.listComment(bd_num);
	}

	@Override
	public void updateComment(String cm_writer, String cm_content, int bd_num, int cm_id) {
		commentMapper.updateComment(cm_writer, cm_content, bd_num, cm_id);
	}

	@Override
	public void deleteComment(int bd_num, int cm_id) {
		commentMapper.deleteComment(bd_num, cm_id);
	}

	@Override
	public CommentVO bestComment(int bd_num) {
		return commentMapper.bestComment(bd_num);
	}

	@Override
	public void likeComment(int bd_num, int cm_id) {
		commentMapper.likeComment(bd_num, cm_id);
	}

}
