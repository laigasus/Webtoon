package com.webtoon.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webtoon.domain.BoardVO;
import com.webtoon.domain.CommentVO;
import com.webtoon.service.BoardService;
import com.webtoon.service.CommentService;
import com.webtoon.service.UserService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	private UserService userService;
	private CommentService commentService;

	// jajak.jsp
	// 커뮤니티 메인페이
	@GetMapping("/jajak")
	public String jajakGET(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		/////////////////////////////////////////////// 페이지에 맞는 게시글
		String category = (String) request.getParameter("category");
		String search = (String) request.getParameter("search");
		if (category == null || search == null) {
			category = "제목";
			search = "";
		}
		int pageSize = 10; // 한페이지에 몇개의 글 출력할지 결정
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);

		int gongziCount = 1; // 공지글의 갯수를 가져옵니다. 기본값 1
		int startRow = (currentPage - 1) * pageSize; // 현재페이지에 첫 번째 글
		int count = 0;
		BoardService forPage = service;
		count = forPage.getCountBoard(); // 총 레코드갯수 반환
		List<BoardVO> list = null;
//		if (count > 0) {
//			list = forPage.listBoard(startRow, pageSize);
//		}
		System.out.println(startRow);

		ArrayList<BoardVO> articlesAdmin = service.AdminListBoard();
		ArrayList<BoardVO> articles = service.listBoard(startRow, pageSize);

		model.addAttribute("articlesAdmin", articlesAdmin);
		model.addAttribute("articles", articles);
		///////////////////////////////////////////////
		/////////////////////////////////////////////// 페이징
		if (count > 0) {
			// 총 페이지의 수를 결정
			int pageCount = (count - gongziCount) / pageSize + ((count - gongziCount) % pageSize == 0 ? 0 : 1);
			// 한 화면안에 페이지에 갯수 넘으면 다음표시
			int pageBlock = 10;
			// 한 페이지에 보여줄 시작 및 끝 번호
			int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
			if (endPage > pageCount) { // 없으면 자료가 15개 있을때 번호가 [1]~[9] 까지 다나옴
				endPage = pageCount;
			}
			if (startPage > pageBlock) { // 페이지 블록수보다 startPage가 클경우 이전 링크 생성

			}
			for (int i = startPage; i <= endPage; i++) { // 페이지 번호 결정
				if (i == currentPage) { // 현재 페이지에는 링크를 설정하지 않음
				} else { // 현재 페이지가 아닌 경우 링크 설정

				}
			}
			if (endPage < pageCount) { // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
			}
		}
		//////////////// 페이징 끝

		return "jajak";
	}

	/// jajak 메인에서 검색시 사용
	@PostMapping("/jajak")
	public String jajakPOST(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		/////////////////////////////////////////////// 페이지에 맞는 게시글
		String category = (String) request.getParameter("category");
		String search = (String) request.getParameter("search");
		if (category == null || search == null) {
			category = "제목";
			search = "";
		}
		ArrayList<BoardVO> articles=service.searchBoard(search, category);
		
		model.addAttribute("articles",articles);
		
		return "jajak";
	}

	// jajak_content_control.jsp
	// 커뮤니티 글에서 댓글 적을때
	@GetMapping("/jajak_content_control")
	public String jajakContentControlGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");

		String session_user_email = (String) session.getAttribute("session_user_email");

		if (session_user_email != null) {
			String cm_content = (String) request.getParameter("cm_content");
			int Bd_num = Integer.parseInt(request.getParameter("Bd_num"));
			String cm_writer = (String) request.getParameter("nick");

			commentService.regist(Bd_num, cm_writer, cm_content, session_user_email);
		}

		model.addAttribute("session_user_email", session_user_email); // 필요 없을수도

		return "jajak_content_control";
	}

	
	@PostMapping("/jajak_content_control")
	public String jajakContentControlPOST() {
		return "jajak_content_control";
	}
	/////////////////////////////////////////////////

	// jajak_content_delete.jsp
	// 커뮤니티 글 삭제
	@GetMapping("/jajak_content_delete")
	public String jajak_content_deleteControlGET(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");

		int Bd_num = Integer.parseInt(request.getParameter("Bd_num")); // jajak_content.jsp페이지에서 Bd_num 받기

		service.deleteBoard(Bd_num);

		return "redirect:/jajak";
	}

	/////////////////////////////////////////////////

	// jajak_content.jsp
	// 글 상세 페이지
	// 댓글 기능 다시확인할 필요가 있음
	@GetMapping("/jajak_content")
	public String jajakContentGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");

		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		String nick = (String) session.getAttribute("session_user_nick");
		String email = (String) session.getAttribute("session_user_email");
		if (email == null) {
			email = "";
		}

		BoardVO articles = service.contentBoard(bd_num);
		service.viewIncrease(articles.getBd_num(), articles.getBd_view());
		String writer = userService.getUserInfo(articles.getBd_email()).getNick();
		Boolean commentEmpty = commentService.listComment(bd_num).isEmpty();

		ArrayList<CommentVO> comments = commentService.listComment(bd_num);
//		String commentor = userService.getUserInfo(((CommentVO) comments).getEmail()).getNick();

		CommentVO best = commentService.bestComment(bd_num);
		String best_commentor = userService.getUserInfo(best.getEmail()).getNick();

		model.addAttribute("articles", articles);
		model.addAttribute("Bd_num", bd_num);
		model.addAttribute("email", email);
		model.addAttribute("nick", nick);
		model.addAttribute("writer", writer);
		model.addAttribute("commentEmpty", commentEmpty);
		model.addAttribute("best", best);
		model.addAttribute("best_commentor", best_commentor);
		model.addAttribute("comments", comments);

		return "jajak_content";
	}

	@PostMapping("/jajak_content")
	public String jajakContentPOST() {
		return "jajak_content";
	}

	
	// jajak_upload.jsp
		// 관리자 페이지 제어 이벤트 페이지
		@GetMapping("/jajak_upload")
		public String jajakUploadGET() {

			return "jajak_upload";
		}
		// 게시글 올리기 확인 버튼을 눌렀을때
		@PostMapping("/jajak_upload")
		public String jajakUploadPOST() {

			return "redirect:jajak";
		}
		
		// jajak_upload_control.jsp
		// 관리자 페이지 제어 이벤트 페이지
		@GetMapping("/jajak_upload_control")
		public String jajakUploadControlGET(HttpServletRequest request, HttpSession session, Model model)
				throws IOException {
			request.setCharacterEncoding("utf-8");

			String email = (String) session.getAttribute("session_user_email");
			String writer = (String) session.getAttribute("session_user_nick");
			boolean check;
			if (email == null) {
				check = false;
			} else {
				check = true;
			}
			String path = request.getSession().getServletContext().getRealPath("fileFolder");
			int size = 1024 * 1024 * 10; // 저장가능한 파일 크기 이미지,파일 업로드 기능 확장시 사용
			String file = ""; // 업로드한 파일 이름(변경될 수 있음)
			String originalFile = ""; // 이름이 변경되었다면 변경되기 전 실제 파일 이름
			if (check) {
				MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
				Enumeration<?> files = multi.getFileNames();
				String str = null; // 파일 이름을 받아와 저장
				file = multi.getFilesystemName(str); // 업로드된 파일 이름 가져옴
				originalFile = multi.getOriginalFileName(str); // 원래 파일 이름 가져옴
				String absoluteImgPath = null;
				String title = multi.getParameter("title");// 제목 가져옴
				String content = multi.getParameter("content");// 본문 가져옴
				int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
				service.updateBoard(title, content, absoluteImgPath, bd_num);
			} else {
				model.addAttribute("check", check);
			}

			return "jajak_upload_control";
		}

		@PostMapping("/jajak_upload_control")
		public String jajakUploadControlPOST() {
			return "jajak_upload_control";
		}


	
	
	// jajak_update_control.jsp
	// 커뮤니티 글 수정
	@GetMapping("/jajak_update_control")
	public String jajakUpdateControlGET(HttpServletRequest request, HttpSession session, Model model)
			throws IOException {
		request.setCharacterEncoding("utf-8");

		String email = (String) session.getAttribute("session_user_email");
		String writer = (String) session.getAttribute("session_user_nick");
		boolean check;
		if (email == null) {
			check = false;
		} else {
			check = true;
		}
		String path = request.getSession().getServletContext().getRealPath("fileFolder");
		int size = 1024 * 1024 * 10; // 저장가능한 파일 크기 이미지,파일 업로드 기능 확장시 사용
		String file = ""; // 업로드한 파일 이름(변경될 수 있음)
		String originalFile = ""; // 이름이 변경되었다면 변경되기 전 실제 파일 이름
		if (check) {
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			String str = null; // 파일 이름을 받아와 저장
			file = multi.getFilesystemName(str); // 업로드된 파일 이름 가져옴
			originalFile = multi.getOriginalFileName(str); // 원래 파일 이름 가져옴
			String absoluteImgPath = null;
			String title = multi.getParameter("title");// 제목 가져옴
			String content = multi.getParameter("content");// 본문 가져옴
			int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
			service.updateBoard(title, content, absoluteImgPath, bd_num);
		} else {
			model.addAttribute("check", check);
		}

		return "jajak_update_control";
	}

	@PostMapping("/jajak_update_control")
	public String jajakUpdateControlPOST() {
		return "jajak_update_control";
	}
	/////////////////////////////////////////////////

	// jajak_update.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/jajak_update")
	public String jajakUpdateGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");

		int content_number = Integer.parseInt(request.getParameter("content_number"));
		BoardVO articles = service.contentBoard(content_number);

		model.addAttribute("articles", articles);

		return "jajak_update";
	}

	@PostMapping("/jajak_update")
	public String jajakUpdatePOST() {
		return "jajak_update";
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
		return "my_post";
	}
	/////////////////////////////////////////////////

}
