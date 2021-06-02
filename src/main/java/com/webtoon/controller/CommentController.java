package com.webtoon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.webtoon.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService service;

	// comment_like_control.jsp
	// 댓글 좋아요 컨트롤
	@GetMapping("/comment_like_control")
	public String commentLikeControlGET(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		request.setCharacterEncoding("utf-8");

		System.out.println("comment_like_control");

		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int cm_id = Integer.parseInt(request.getParameter("cm_id"));
		String writer = request.getParameter("writer");
		service.likeComment(bd_num, cm_id);

		return ("forward:jajak_content?Bd_num=" + bd_num + "&nick=" + writer + "#comment-h1");
	}

	/////////////////////////////////////////////////

}
