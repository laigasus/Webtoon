package com.webtoon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webtoon.domain.BoardVO;
import com.webtoon.service.BoardService;


@Controller
public class BoardController {

	
	@Autowired
	private BoardService service; 
	
	
	// 커뮤니티 글 목록     시작글번호와 표현할 글 갯수가
	@GetMapping("jajak")
	public String jajakListView(Model model,int startRow,int pageSize) {
		System.out.println("jajakListView start ====");
		// 페이징은 니중에 처리
		List<BoardVO> boardList = service.listBoard(startRow, pageSize);
		
		model.addAttribute("boardList",boardList);
		return "jajak";
	}
	
	//커뮤니티 특정글 선택 후 화면
	@GetMapping("jajak_content")
	public String jajakContentView(Model model,BoardVO vo) {
		//bd_num을 받아와야함
		BoardVO boardContent = service.contentBoard(vo.getBd_num());
		model.addAttribute("boardContent",boardContent);
		
		return "jajak_content";
	}
	
	// 커뮤니티 글올리기 페이지
	@GetMapping("jajak-upload")
	public String jajakUploadView(Model model) {
		
		return "jajak-upload";
	}
	// 커뮤니티 글 올리기 확인 눌렀을때
	@PostMapping("jajak-upload")
	public String jajakUpload(BoardVO vo) {
//		service.regist(email, writer, title, content, absoluteImgPath);
		service.regist(vo.getBd_email(), vo.getBd_writer(), vo.getBd_title(), vo.getBd_content(), null);
		return "redirect:/jajak";
	}

	//글 수정하는 페이지
	@GetMapping("jajak-update")
	public String jajakUpdateView(Model model,BoardVO vo) {
		System.out.println("jajakUpdateView start ====");		
//		void updateBoard(String title, String content, String img, int bId);
		service.updateBoard(vo.getBd_title(), vo.getBd_content(), null, vo.getBd_num());
		return "jajak-update";
	}
	
	
	// 글 삭제
	@RequestMapping("/jajak_delete")
	public String delete(BoardVO vo) {
		System.out.println("delete..... "+ vo);
		
		service.deleteBoard(vo.getBd_num());
		return "redirect:/jajak";
	}
	
	
}
