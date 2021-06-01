package com.webtoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webtoon.service.CommentService;
import com.webtoon.external.*;

@Controller
public class CommentController2 {

	@Autowired
	private CommentService service;

	// comment_like_control.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/comment_like_control")
	public String commentLikeControlGET() {

		return "";
	}

	@PostMapping("/comment_like_control")
	public String commentLikeControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////
	
	

}
