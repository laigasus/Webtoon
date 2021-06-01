package com.webtoon.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webtoon.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService service;

	// comment_like_control.jsp
	// 댓글 좋아요 컨트롤
	@GetMapping("/comment_like_control")
	public String commentLikeControlGET(HttpServletRequest request,HttpSession session ,Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		int bd_num=Integer.parseInt(request.getParameter("Bd_num"));
		int cm_id=Integer.parseInt(request.getParameter("cm_id"));
		String writer=request.getParameter("writer");
		service.likeComment(bd_num, cm_id);

		model.addAttribute("bd_num",bd_num);
		model.addAttribute("writer",writer);
		
		return "comment_like_control";
	}

	@PostMapping("/comment_like_control")
	public String commentLikeControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////
	
	

}
