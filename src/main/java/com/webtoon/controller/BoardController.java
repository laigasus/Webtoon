package com.webtoon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	private BoardService boardService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;

	// jajak.jsp
	// 커뮤니티 메인페이지
	@GetMapping("/jajak")
	public String jajakGET(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		/////////////////////////////////////////////// 페이지에 맞는 게시글 삽입
		String category = (String) request.getParameter("category");
		String search = (String) request.getParameter("search");
		if (category == null || search == null) {
			category = "제목";
			search = "";
		}
		int pageSize = 10; // 한페이지에 몇개의 글 출력할지 결정
		int gongziCount = 1; // 공지글의 갯수를 가져옵니다. 기본값 1

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize; // 현재페이지에 첫 번째 글
		int count = 0;
		BoardService forPage = boardService;
		count = forPage.getCountBoard(); // 총 레코드갯수 반환

		ArrayList<BoardVO> articlesAdmin = boardService.AdminListBoard();
		ArrayList<BoardVO> articles = boardService.listBoard(startRow, pageSize);

		model.addAttribute("articlesAdmin", articlesAdmin);
		model.addAttribute("articles", articles);
		/////////////////////////////////////////////// 페이징
		int startPage = 0;
		int endPage = 0;
		int pageCount = 0;
		int pageBlock = 10;
		ArrayList<Integer> startToEndPage = new ArrayList<Integer>(); // jstl에 넘겨줄 배열
		if (count > 0) {
			// 총 페이지의 수를 결정
			pageCount = (count - gongziCount) / pageSize + ((count - gongziCount) % pageSize == 0 ? 0 : 1);
			// 한 화면안에 페이지에 갯수 넘으면 다음표시
			// 한 페이지에 보여줄 시작 및 끝 번호
			startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
			endPage = startPage + pageBlock - 1; // 현재 화면의 끝 페이지
			// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
			if (endPage > pageCount) { // 없으면 자료가 15개 있을때 번호가 [1]~[9] 까지만 나옴
				endPage = pageCount;
			}

			for (int i = startPage; i <= endPage; i++) { // 페이지 번호 결정
				startToEndPage.add(i);
			}
		}

		model.addAttribute("startToEndPage", startToEndPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageBlock", pageBlock);
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
		ArrayList<BoardVO> articles = boardService.searchBoard(search, category);

		model.addAttribute("articles", articles);

		return "jajak";
	}

	// jajak_content_control.jsp
	// 커뮤니티 글에서 댓글 적을때
	@PostMapping("/jajak_comment_control")
	public String jajakContentControlPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String session_user_email = (String) session.getAttribute("session_user_email");

		if (session_user_email == null || session_user_email == "") {
			out.print("<script>");
			out.print("alert('로그인해주세요');");
			out.print("</script>");
			out.flush();
			out.close();
			return "login";
		} else {
			String cm_content = (String) request.getParameter("cm_content");
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			String cm_writer = (String) request.getParameter("nick");
			commentService.regist(bd_num, cm_writer, cm_content, session_user_email);
			return ("redirect:jajak_content?bd_num=" + bd_num);
		}

	}
	/////////////////////////////////////////////////

	// jajak_content.jsp
	// 글 상세 페이지
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

		BoardVO articles = boardService.contentBoard(bd_num);
		boardService.viewIncrease(articles.getBd_num(), articles.getBd_view());

		String writer = (String) userService.getUserInfo(articles.getBd_email()).getNick(); //
		ArrayList<CommentVO> comments = commentService.listComment(bd_num);
		Boolean commentEmpty = comments.isEmpty();

		if (!commentEmpty) { // 댓글이 존재하면
			CommentVO best = commentService.bestComment(bd_num);
			String best_commentor = userService.getUserInfo(best.getEmail()).getNick();
			model.addAttribute("best", best);
			model.addAttribute("best_commentor", best_commentor);
		}

		model.addAttribute("articles", articles);
		model.addAttribute("bd_num", bd_num);
		model.addAttribute("email", email);
		model.addAttribute("nick", nick);
		model.addAttribute("writer", writer);
		model.addAttribute("commentEmpty", commentEmpty);
		model.addAttribute("comments", comments);

		return "jajak_content";
	}

	// jajak_upload.jsp
	@GetMapping("/jajak_upload")
	public String jajakUploadGET() {

		return "jajak_upload";
	}

	// jajak_upload_control.jsp
	// 게시글 올리기 확인 버튼을 눌렀을때
	@PostMapping("/jajak_upload_control")
	public String jajakUploadControlGET(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String email = (String) session.getAttribute("session_user_email");
		String writer = (String) session.getAttribute("session_user_nick");
		System.out.println("email " + email);
		System.out.println("writer " + writer);
		boolean check;
		if (email == null) {
			check = false;
		} else {
			check = true;
		}
		String path = request.getSession().getServletContext().getRealPath("/resources/uploads");
		int size = 1024 * 1024 * 10; // 저장가능한 파일 크기 이미지,파일 업로드 기능 확장시 사용
		String file = ""; // 업로드한 파일 이름(변경될 수 있음)
		String originalFile = ""; // 이름이 변경되었다면 변경되기 전 실제 파일 이름
		if (check) {
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			String str = null; // 파일 이름을 받아와 저장
			file = multi.getFilesystemName(str); // 업로드된 파일 이름 가져옴
			originalFile = multi.getOriginalFileName(str); // 원래 파일 이름 가져옴
			String absoluteImgPath = null; // path+'\\'+file; 이미지 업로드 기능 확장시 사용
			String title = multi.getParameter("title");// 제목 가져옴
			String content = multi.getParameter("content");// 본문 가져옴
			boardService.regist(email, writer, title, content, absoluteImgPath);
			System.out.println("check  " + check);
			model.addAttribute("check", check);

			out.println("<script>");
			out.println("location.href='/jajak';");
			out.println("</script>");
			out.flush();
			out.close();

			return "jajak";

		} else {
			out.println("<script>");
			out.println("alert('로그인해주세요');");
			out.println("location.href('/login');");
			out.println("</script>");
			out.flush();
			out.close();
		}
		return "jajak";
	}

	// jajak_update.jsp
	// 게시판 글 수정 화면
	@GetMapping("/jajak_update")
	public String jajakUpdateGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");

		int content_number = Integer.parseInt(request.getParameter("content_number"));
		BoardVO articles = boardService.contentBoard(content_number);

		model.addAttribute("articles", articles);

		return "jajak_update";
	}
	////////////////////////////////

	// jajak_update_control.jsp
	// 게시판 글 수정 제어
	@PostMapping("/jajak_update_control")
	public String jajakUpdateControlPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String email = (String) session.getAttribute("session_user_email");
		String writer = (String) session.getAttribute("session_user_nick");
		boolean check;
		if (email == null) {
			check = false;
		} else {
			check = true;
		}
		String path = request.getSession().getServletContext().getRealPath("/resources/uploads");
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
			System.out.println("multi  " + multi.toString());
			System.out.println("multi.getParameter(\"title\")  " + multi.getParameter("title"));
			System.out.println("request.getParameter(\"r\")  " + multi.getParameter("bd_num"));
			int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
			boardService.updateBoard(title, content, absoluteImgPath, bd_num);

			System.out.println("check====  " + check);
			model.addAttribute("check", check);
			return "redirect:jajak";
		} else {
			out.println("<script>");
			out.println("alert('로그인해주세요');");
			out.println("location.href('/login');");
			out.println("</script>");
			out.flush();
			out.close();
		}
		return "redirect:jajak";
	}
	////////////////

	// jajak_content_delete.jsp
	// 커뮤니티 글 삭제
	@GetMapping("/jajak_content_delete")
	public String jajak_content_deleteControlGET(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");

		int bd_num = Integer.parseInt(request.getParameter("bd_num"));

		boardService.deleteBoard(bd_num);

		return "redirect:/jajak";
	}
	/////////////////////////////////////////////////

}
