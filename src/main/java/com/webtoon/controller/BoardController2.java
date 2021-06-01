package com.webtoon.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webtoon.domain.BoardVO;
import com.webtoon.service.BoardService;

@Controller
public class BoardController2 {

	@Autowired
	private BoardService service;

	// jajak_content_control.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_content_control")
	public String jajakContentControlGET() {

		return "";
	}

	@PostMapping("/jajak_content_control")
	public String jajakContentControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak_content_delete.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_content_delete")
	public String jajakContentDeleteGET() {

		return "";
	}

	@PostMapping("/jajak_content_delete")
	public String jajakContentDeletePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak_content.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_content")
	public String jajakContentGET() {

		return "";
	}

	@PostMapping("/jajak_content")
	public String jajakContentPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak_update_control.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_update_control")
	public String jajakUpdateControlGET() {

		return "";
	}

	@PostMapping("/jajak_update_control")
	public String jajakUpdateControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak_update.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_update")
	public String jajakUpdateGET() {

		return "";
	}

	@PostMapping("/jajak_update")
	public String jajakUpdatePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak_upload_control.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_upload_control")
	public String jajakUploadControlGET() {

		return "";
	}

	@PostMapping("/jajak_upload_control")
	public String jajakUploadControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak_upload.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_upload")
	public String jajakUploadGET() {

		return "";
	}

	@PostMapping("/jajak_upload")
	public String jajakUploadPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// jajak.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak")
	public String jajakGET() {

		return "";
	}

	@PostMapping("/jajak")
	public String jajakPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// my_post.jsp
	// 페이지 설명 추가
	@GetMapping("/my_post")
	public String myPostGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String admin_user_email = (String) session.getAttribute("admin_user_email");
		String email = "";
		if (admin_user_email == null) {
			admin_user_email = "";
		} // 널이여도 equals 오류없게
			// 사용자가 로그인하여 세션에 닉네임이나 이메일 둘중 프라이머리키가 등록되어있는 상태
		if (admin_user_email.equals("")) { // 일반사용자가 접근
			email = (session.getAttribute("session_user_email") != null)
					? (String) session.getAttribute("session_user_email")
					: "";
		} else { // 관리자가 들어온 마이페이지
			email = admin_user_email;
		}
		if (!email.equals(null)) {
			ArrayList<BoardVO> post = service.myListBoard(email);
			model.addAttribute(post);
		}

		return "";
	}

	@PostMapping("/my_post")
	public String myPostPOST() {
		return "";
	}
	/////////////////////////////////////////////////

}
