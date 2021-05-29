package com.webtoon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String jajakContentView(Model model,int bId) {
		
		//bd_num을 받아와야함
		BoardVO boardContent = service.contentBoard(bId);
		
		model.addAttribute("boardContent",boardContent);
		
		return "jajak_content";
	}
	
	//글 수정하는 페이지
	@GetMapping("jajak_update")
	public String jajakUpdateView(Model model,String title,String content, String img, int bId) {
		System.out.println("jajakUpdateView start ====");
		
		service.updateBoard(title, content, img, bId);
		
		
		return "jajak_update";
	}
	
	
	// 글 삭제
	@RequestMapping("/jajak_delete")
	public String delete(int bId) {
		System.out.println("delete..... "+ bId);
		
		service.deleteBoard(bId);
		return "redirect:/jajak";
	}
	
//	@Override
//	public List<BoardVO> listBoard(int startRow, int pageSize) {
//		return boardMapper.listBoard(startRow, pageSize);
//	}
	
	
//	@GetMapping("/list")
//	public String listView(Model model, HttpServletRequest request) {
//		System.out.println("listView start ====");
//		//HttpSession session = requset.getSession();
//		//String userName = (String) session.getAttribute("userName");
//		List<BoardVO> boardList = service.getListBoard();
//		for (BoardVO boardVO : boardList) {
//			System.out.println("list.. " + boardVO);
//		}
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}
//	
//	@PostMapping("/list")
//	public String list(SearchVO searchvo,Model model) {
//		System.out.println("list search start ======== " + searchvo);
//		List<BoardVO> boardList = service.getListSearch(searchvo);
//		for (BoardVO boardVO : boardList) {
//			System.out.println("list.. " + boardVO);
//		}
//		model.addAttribute("boardList",boardList);
//		return "getBoardList";
//	}
	
	
}
