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

import com.webtoon.domain.UserVO;
import com.webtoon.service.UserService;

@Controller
public class UserController2 {

	@Autowired
	private UserService service;

	// admin_page_control.jsp
	// 관리자 페이지 제어 이벤트 페이지
	@GetMapping("/admin_page_control")
	public String adminPageControlGET(HttpServletRequest request) {
		String email[] = request.getParameterValues("email");
		for (int i = 0; i < email.length; i++) {
			service.deleteUser(email[i]);
		}
		return "";
	}

	@PostMapping("/admin_page_control")
	public String adminPageControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// admin_page.jsp
	// 관리자 페이지 제어 페이지
	@GetMapping("/admin_page")
	public String adminPageGET(Model model) {
		ArrayList<UserVO> users = service.listUser();
		model.addAttribute(users);

		return "";
	}

	@PostMapping("/admin_page")
	public String adminPagePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// admin_user_mypage.jsp
	// admin_page.jsp에서 a 태그로 값을 받아서 mypage로 가기전에 넘겨주는 페이지입니다.

	@GetMapping("/admin_user_mypage")
	public String adminUserMypageGET(HttpServletRequest request, HttpSession session, Model model) {
		String admin_user_email = request.getParameter("admin_user_email");
		session.setAttribute("admin_user_email", admin_user_email);
		// response.sendRedirect("mypage.jsp");
		return "";
	}

	@PostMapping("/admin_user_mypage")
	public String adminUserMypagePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// deleteAccount_control.jsp
	// 페이지 설명 추가
	@GetMapping("/deleteAccount_control")
	public String deleteAccountControlGET(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");

		String email = (String) request.getAttribute("session_user_email");
		String password = request.getParameter("password");

		int result = service.userCheck(email, password);
		if (result == 0) {

		} else {
			service.deleteUser(email);
			// session.invalidate();
		}
		model.addAttribute(result);
		return "";
	}

	@PostMapping("/deleteAccount_control")
	public String deleteAccountControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// deleteAccount.jsp
	// 페이지 설명 추가
	@GetMapping("deleteAccount")
	public String deleteAccountGET() {

		return "";
	}

	@PostMapping("/deleteAccount")
	public String deleteAccountPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// email_duplicate_control.jsp
	// 페이지 설명 추가
	@GetMapping("/email_duplicate_control")
	public String emailDuplicateControlGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		// 전달 받은 이메일이 db에서 중복된 이메일인지 확인 후 true or false return
		boolean check = service.CheckDuplicate(email);
		session.setAttribute("check", check);

		if (check) {
			// 중복 이메일이 있으면 false를 등록
			session.setAttribute("check", false);
			// response.sendRedirect("register.jsp");
		} else {
			// 이메일 사용가능시 세션에 확인한 이메일과 check에 true값을 등록
			session.setAttribute("check", true);
			session.setAttribute("checkedemail", email);
			// response.sendRedirect("email_duplicate.jsp");
		}
		return "";

	}

	@PostMapping("/email_duplicate_control")
	public String emailDuplicateControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// email_duplicate_result.jsp
	// 페이지 설명 추가
	@GetMapping("/email_duplicate_result")
	public String emailDuplicateResultGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		boolean check = Boolean.parseBoolean(request.getParameter("check"));
		String checkedEmail = request.getParameter("checkedemail");
		if (check) {
			// 중복 이메일이 있으면 false를 등록
			session.setAttribute("check", false);
			// response.sendRedirect("register.jsp");
		} else {
			// 이메일 사용가능시 세션에 확인한 이메일과 check에 true값을 등록
			session.setAttribute("check", true);
			session.setAttribute("checkedemail", checkedEmail);
			// response.sendRedirect("email_duplicate.jsp");
		}

		return "";
	}

	@PostMapping("/email_duplicate_result")
	public String emailDuplicateResultPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// email_duplicate.jsp
	// 페이지 설명 추가
	@GetMapping("/email_duplicate")
	public String emailDuplicateGET() {

		return "";
	}

	@PostMapping("/email_duplicate")
	public String emailDuplicatePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// login_control.jsp
	// 페이지 설명 추가
	@GetMapping("/login_control")
	public String loginControlGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String check = request.getParameter("saveCheck"); // 아이디비번저장 체크박스가 체크되었는지를 확인하여서 체크되면 "saveCheck"="1" 를가져오고 아니면
															// null
		if (check == null) { // equals 오류방지
			check = "";
		}
		int result = service.userCheck(email, password);

		if (check.equals("checked")) { // check가 됐으면
			if (result == -1) {

			} else if (result == 0) {

			} else { // 로그인 성공
				UserVO vo = service.getUserInfo(email);
				session.setAttribute("session_user_email", email);
				session.setAttribute("session_user_password", password);
				session.setAttribute("session_user_nick", vo.getNick());
				session.setAttribute("chSave", "checked"); // chSave가 "checked" 을 가지면 login.jsp에서 체크상태가 유지됨
				session.setAttribute("saveId", email);
				session.setAttribute("savePw", password);
				// response.sendRedirect("index.jsp");
			}
		} else { // 체크 되면
			if (result == -1) {

			} else if (result == 0) {

			} else { // 로그인 성공
				UserVO vo = service.getUserInfo(email);
				session.setAttribute("session_user_email", email);
				session.setAttribute("session_user_password", password);
				session.setAttribute("session_user_nick", vo.getNick());
				session.removeAttribute("chSave"); // 체크 안됐으니 지우기
				session.removeAttribute("saveId");
				session.removeAttribute("savePw");
				// response.sendRedirect("index.jsp");
			}
		}

		model.addAttribute(check);
		model.addAttribute(result);

		return "";
	}

	@PostMapping("/login_control")
	public String loginControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// login.jsp
	// 페이지 설명 추가
	@GetMapping("/login")
	public String loginGET(HttpServletRequest request, HttpSession session, Model model) {
		String chSave=(String)session.getAttribute("chSave"); //체크했으면 checked 저장
		String saveId=(String)session.getAttribute("saveId");  //saveId
		String savePw=(String)session.getAttribute("savePw");
		if(saveId==null){ //saveId가 비어있어서 null이 보여지는 것 방지
			saveId="";
			savePw="";
		}
		return "";
	}

	@PostMapping("/login")
	public String loginPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// logout.jsp
	// 페이지 설명 추가
	@GetMapping("/logout")
	public String logoutGET(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		//response.sendRedirect("index.jsp");
		return "";
	}

	@PostMapping("/logout")
	public String logoutPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	
	// my_webtoon_add.jsp
	// 페이지 설명 추가
	@GetMapping("/my_webtoon_add")
	public String myWebtoonAddGET() {

		return "";
	}

	@PostMapping("/my_webtoon_add")
	public String myWebtoonAddPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// my_webtoon_list.jsp
	// 페이지 설명 추가
	@GetMapping("/my_webtoon_list")
	public String my_webtoonListGET() {

		return "";
	}

	@PostMapping("/myWebtoonList")
	public String myWebtoonListPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// mypage.jsp
	// 페이지 설명 추가
	@GetMapping("/mypage")
	public String mypageGET() {

		return "";
	}

	@PostMapping("/mypage")
	public String mypagePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// nick_change_control.jsp
	// 페이지 설명 추가
	@GetMapping("/nick_change_control")
	public String nickChangeControlGET() {

		return "";
	}

	@PostMapping("/nick_change_control")
	public String nickChangeControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// nick_change.jsp
	// 페이지 설명 추가
	@GetMapping("/nick_change")
	public String nickChangeGET() {

		return "";
	}

	@PostMapping("/nick_change")
	public String nickChangePOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// pw_after.jsp
	// 페이지 설명 추가
	@GetMapping("/pw_after")
	public String pwAfterGET() {

		return "";
	}

	@PostMapping("/pw_after")
	public String pwAfterPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// pw_control.jsp
	// 페이지 설명 추가
	@GetMapping("/pw_control")
	public String pwControlGET() {

		return "";
	}

	@PostMapping("/pw_control")
	public String pwControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// pw_find.jsp
	// 페이지 설명 추가
	@GetMapping("/pw_find")
	public String pwFindGET() {

		return "";
	}

	@PostMapping("/admin_page")
	public String pwFindPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// register_control.jsp
	// 페이지 설명 추가
	@GetMapping("/register_control")
	public String registerControlGET() {

		return "";
	}

	@PostMapping("/register_control")
	public String registerControlPOST() {
		return "";
	}
	/////////////////////////////////////////////////

	// register.jsp
	// 페이지 설명 추가
	@GetMapping("/register")
	public String registerGET() {

		return "";
	}

	@PostMapping("/admin_page")
	public String registerPOST() {
		return "";
	}
	/////////////////////////////////////////////////
}
