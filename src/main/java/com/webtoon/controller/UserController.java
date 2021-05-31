package com.webtoon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webtoon.domain.UserVO;
import com.webtoon.service.UserService;

@Controller
public class UserController {

	
	@Autowired
	private UserService service;
	
	@GetMapping("login")
	public String loginView() {
		
		return "login";
	}
	
	@PostMapping("login")
	public String login(UserVO vo,HttpServletRequest req,RedirectAttributes rttr) {
		
		HttpSession session = req.getSession();
		// 존재하는 유저인지 체크
		int userCheck = service.userCheck(vo.getEmail(), vo.getPw());
		
		if (userCheck == 1) {  //성공
			//세션에 아이디 저장
			session.setAttribute("email", vo.getEmail());
			session.setAttribute("pw", vo.getPw());
			session.setAttribute("nick", vo.getNick());
			
		}else { //실패

			return "redirect:/login";
		}
		return "redirect:/index";
	}
	
	//비밀번호찾기 페이지
	@GetMapping("/pw_find")
	public String pwFindView() {
		
		return "pw_find";
	}
	
	//비밀번호 찾기
	@PostMapping("/pw_find")
	public String pwFind() {
		
		return "redirect:/login";
	}
	
	//로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/index";
	}
	
	
	//회원가입
	@GetMapping("/register")
	public String registerView(){
		
		
		return "register";
	}
	
	
	
	
	
//	@GetMapping("jajak-update")
//	public String jajakUpdateView(Model model,BoardVO vo) {
//		System.out.println("jajakUpdateView start ====");		
////		void updateBoard(String title, String content, String img, int bId);
//		service.updateBoard(vo.getBd_title(), vo.getBd_content(), null, vo.getBd_num());
//		return "jajak-update";
//	}
//	
//	
//	// 글 삭제
//	@RequestMapping("/jajak_delete")
//	public String delete(BoardVO vo) {
//		System.out.println("delete..... "+ vo);
//		
//		service.deleteBoard(vo.getBd_num());
//		return "redirect:/jajak";
//	}
	
	
}
