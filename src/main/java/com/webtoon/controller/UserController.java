package com.webtoon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.webtoon.domain.BoardVO;
import com.webtoon.domain.MyWebtoonVO;
import com.webtoon.domain.UserVO;
import com.webtoon.external.DBConnect;
import com.webtoon.external.SMTPAuthenticatior;
import com.webtoon.service.BoardService;
import com.webtoon.service.UserService;
import com.webtoon.service.WebtoonService;

@Controller
@SessionAttributes("*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private WebtoonService webtoonService;
	@Autowired
	private BoardService boardService;

	// admin_page_control
	// 관리자 페이지 제어 이벤트 페이지
	@PostMapping("/admin_page_control")
	public String adminPageControlPOST(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String email[] = request.getParameterValues("email");
		for (int i = 0; i < email.length; i++) {
			System.out.println("email===" + email[i]);
			userService.deleteUser(email[i]);
		}

		out.println("<script>");
		out.println("alert('회원탈퇴가 완료되었습니다');");
		out.println("location.href='admin_page';");
		out.println("</script>");
		out.flush();
		out.close();

		return "redirect:admin_page";
	}
	/////////////////////////////////////////////////

	// admin_page.jsp
	// 관리자 페이지
	@GetMapping("/admin_page")
	public String adminPageGET(Model model) {
		ArrayList<UserVO> users = userService.listUser();
		model.addAttribute("users", users);

		return "admin_page";
	}
	/////////////////////////////////////////////////

	// admin_user_mypage
	// admin_page.jsp에서 원하는 사용자의 마이페이지를 보는 페이지입니다.
	@GetMapping("/admin_user_mypage")
	public String adminUserMypageGET(HttpServletRequest request, HttpSession session, Model model) {
		String admin_user_email = request.getParameter("admin_user_email");
		session.setAttribute("admin_user_email", admin_user_email);
		return "redirect:mypage";
	}
	/////////////////////////////////////////////////

	// deleteAccount_control
	//
	@PostMapping("/deleteAccount_control")
	public String deleteAccountControlPOST(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		System.out.println("deleteAccount_controlPost");

		String email = (String) session.getAttribute("session_user_email");
		String password = request.getParameter("password");
		System.out.println("session email" + email);
		System.out.println("password" + email);

		int result = userService.userCheck(email, password);
		if (result == 0) {
			out.println("<script>");
			out.println("alert('비밀번호를 다시 입력해주세요..');");
			out.println("location.href='/deleteAccount';");
			out.println("</script>");
			out.flush();
			out.close();
			return "deleteAccount";

		} else {
			userService.deleteUser(email);
			session = request.getSession();
			session.invalidate(); // 로그아웃

			out.println("<script>");
			out.println("alert('회원탈퇴가 완료되었습니다');");
			out.println("location.href='/';");
			out.println("</script>");
			out.flush();
			out.close();

			return "";
		}

	}
	/////////////////////////////////////////////////

	// deleteAccount
	// 회원탈퇴 페이지
	@GetMapping("deleteAccount")
	public String deleteAccountGET() {

		return "deleteAccount";
	}
	/////////////////////////////////////////////////

	// email_duplicate_control.jsp
	// 이메일중복검사
	@GetMapping("/email_duplicate_control")
	public String emailDuplicateControlGET() {
		return "/email_duplicate_control";
	}

	@PostMapping("/email_duplicate_control")
	public String emailDuplicateControlPOST(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		// 전달 받은 이메일이 db에서 중복된 이메일인지 확인 후 true or false return
		boolean check = userService.CheckDuplicate(email);
		session.setAttribute("check", check);

		if (check == false) {
			// 중복 이메일이 있으면 false를 등록
			session.setAttribute("check", false);
			return "redirect:register";
		} else {
			// 이메일 사용가능시 세션에 확인한 이메일과 check에 true값을 등록
			session.setAttribute("check", true);
			session.setAttribute("checkedemail", email);
			return "redirect:register";
		}

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
			return "redirect:register";
		} else {
			// 이메일 사용가능시 세션에 확인한 이메일과 check에 true값을 등록
			session.setAttribute("check", true);
			session.setAttribute("checkedemail", checkedEmail);
			return "redirect:email_duplicate";
		}
	}

	@PostMapping("/email_duplicate_result")
	public String emailDuplicateResultPOST() {
		return "email_duplicate_result";
	}
	/////////////////////////////////////////////////

	// email_duplicate.jsp
	// 페이지 설명 추가
	@GetMapping("/email_duplicate")
	public String emailDuplicateGET() {

		return "email_duplicate";
	}

	@PostMapping("/email_duplicate")
	public String emailDuplicatePOST() {
		return "email_duplicate";
	}
	/////////////////////////////////////////////////

	// login_control
	// 로그인 컨트롤러
	@PostMapping("/login_control")
	public String loginControlPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int result = userService.userCheck(email, password);
		System.out.println("result: " + result);

		if (result == -1) {
			out.println("<script>");
			out.println("alert('없는 이메일입니다');");
			out.println("location.href='/login';");
			out.println("</script>");
			out.flush();
			out.close();

			return "login";

		} else if (result == 0) {
			out.println("<script>");
			out.println("alert('비밀번호를 다시 입력해주세요');");
			out.println("location.href='/login';");
			out.println("</script>");
			out.flush();
			out.close();

			return "login";

		} else { // 로그인 성공
			UserVO vo = userService.getUserInfo(email);
			session.setAttribute("session_user_email", email);
			session.setAttribute("session_user_password", password);
			session.setAttribute("session_user_nick", vo.getNick());
		}

		return "redirect:/";
	}
	/////////////////////////////////////////////////

	// login.jsp
	// 로그인 페이지
	@GetMapping("/login")
	public String loginGET(HttpServletRequest request, HttpSession session, Model model) {

		return "login";
	}
	/////////////////////////////////////////////////

	// logout
	// 로그아웃 수행
	@GetMapping("/logout")
	public String logoutGET(HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	/////////////////////////////////////////////////
	// my_webtoon_add
	// 찜하기 컨트롤러
	@PostMapping("/my_webtoon_add")
	public String myWebtoonAddPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String imgSrc = request.getParameter("imgSrc");
		String webtoonTitle = request.getParameter("webtoonTitle");
		String webtoonUrl = request.getParameter("webtoonUrl");
		String userEmail;
		if (session.getAttribute("session_user_email") == null) {
			userEmail = "";
		} else {
			userEmail = (String) session.getAttribute("session_user_email");
		}
		int likeCheck = webtoonService.myWebtoonCheck(webtoonTitle, userEmail); // like좋아요 했으면 1반환

		if (!userEmail.equals("") && likeCheck == 1) { // 좋아요를 이미 눌렀고 로그인을 한상황
			webtoonService.myWebtoonDelete(webtoonTitle, userEmail); // insert into 4가지 인자 삽입
			out.println("<script>");
			out.println("alert('좋아요를 취소하였습니다.♥');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
			String referer = request.getHeader("Referer");
			return referer;
		} else if (!userEmail.equals("")) { // 좋아요 안눌렀고 로그인 한상황
			webtoonService.myWebtoonUpload(webtoonTitle, userEmail, imgSrc, webtoonUrl); // insert into 4가지 인자 삽입
		} else {
			// 로그인 해주세요 실행
			out.println("<script>");
			out.println("alert('로그인 해주세요');");
			out.println("</script>");
			out.flush();
			out.close();
			return "login";
		}
		// 이전페이지로 되돌아감
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	/////////////////////////////////////////////////

	// my_webtoon_list
	// mypage 웹툰 리스트
	@GetMapping("/my_webtoon_list")
	public String my_webtoonListGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {

		return "my_webtoon_list";
	}
	/////////////////////////////////////////////////

	// mypage.jsp
	// 마이페이지
	@GetMapping("/mypage")
	public String mypageGET(HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		// 자신이 올린 글 목록 출력
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
			ArrayList<BoardVO> post = boardService.myListBoard(email);
			model.addAttribute("post", post);

		}
		//////////////////////////////////////////
		System.out.println("email: " + email);
		ArrayList<MyWebtoonVO> webtoons = webtoonService.getMyWebtoonList(email);
		model.addAttribute("webtoons", webtoons);

		return "mypage";
	}
	/////////////////////////////////////////////////

	// nick_change_control
	// 닉네임 변경
	@PostMapping("/nick_change_control")
	public String nickChangeControlPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String email = (String) session.getAttribute("session_user_email");
		String password = (String) session.getAttribute("session_user_password");
		String nickname = request.getParameter("nickname");
		UserVO vo = new UserVO(email, nickname, password);

		userService.updateUser(vo);
		session.removeAttribute("session_user_nick");
		session.setAttribute("session_user_nick", nickname);

		out.println("<script>");
		out.println("alert('닉네임이 성공적으로 변경되었습니다.');");
		out.println("</script>");
		out.flush();
		out.close();

		return "/mypage";
	}
	/////////////////////////////////////////////////
	
	// nick_change.jsp
	// 닉네임변경 페이지
	@GetMapping("/nick_change")
	public String nickChangeGET() {

		return "nick_change";
	}
	/////////////////////////////////////////////////

	// pw_after.jsp
	// 비밀번호 변경후 페이지
	@GetMapping("/pw_after")
	public String pwAfterGET() {
		return "pw_after";
	}
	/////////////////////////////////////////////////

	// pw_control
	// 비밀번호 변경
	@PostMapping("/pw_control")
	public String pwControlPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String session_user_email = (String) session.getAttribute("session_user_email"); // 로그인안하면 null
		int userCheck = userService.userCheckEmail(email);

		if (session_user_email == null || session_user_email == "") { // 로그인중 비번을 몰라서 왔을때
			if (userCheck == 1) { // 이메일일치
				out.println("<script>");
				out.println("alert('회원님의 임시비밀번호가 이메일로 보내졌습니다.');");
				out.println("location.href='/';");
				out.println("</script>");
				out.flush();
				out.close();
			} else { // userCheck -1 받았던가 걍 실패
				out.println("<script>");
				out.println("alert('유효하지 않은 이메일입니다.');");
				out.println("location.href = '/pw_find';");
				out.println("</script>");
				out.flush();
				out.close();
			}
		} else { // 이미 로그인을 했을때 비번을 바꾸려고 할때
			if (session_user_email.equals(email)) {
				out.println("<script>");
				out.println("alert('회원님의 임시비밀번호가 이메일로 보내졌습니다.');");
				out.println("location.href='/';");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('유효하지 않은 이메일입니다.');");
				out.println("location.href = '/pw_find';");
				out.println("</script>");
				out.flush();
				out.close();
			}
		}

		String from = "laigasus98@gmail.com";// 관리자 이메일 보낼 이메일 laigasus98@gmail.com
		// 요즘 이메일은 보안상 기본적으로 SMTP를 지원하지 않는다. 해당 플랫폼 설정에서 포트를 개방해야 이메일을 받을 수 있음!!
		Properties p = new Properties(); // 정보를 담을 객체

		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465"); // gmail은 port 465 사용 구글에서 네이버로 보내면 구글것만 있으면 되는거야
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465"); // gmail은 port 465 사용
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		DBConnect conn = new DBConnect();
		PreparedStatement pstmt = null;

		int check = 1;
		Random random = new Random(); // 랜덤 객체 생성(디폴트 시드값 : 현재시간)
		random.setSeed(System.currentTimeMillis()); // 시드값 설정을 따로 할수도 있음 일단은 시간에 따라 변화

		String password = Integer.toString(random.nextInt(9999999)); // 9999999이하 난수

		String sql = "update toon_user set pw=? where email=? ";
		// email=?2 인 이메일에 비번을 pw ?1 로 수정 이떄 pw는 데이터베이스에 칼럼이름과 일치해야함
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {// 오류시
			System.out.println("Database 연결중 에러가 발생 했습니다.");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 여기부터는 이메일을 보내는 코드입니다.
		try {
			Authenticator auth = new SMTPAuthenticatior();
			Session ses = Session.getInstance(p, auth);

			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체

			msg.setSubject("툰스토어에서 임시비밀번호를 보냈습니다."); // 제목 받는 사람에게 가장먼저 보여질 제목입니다.

			StringBuffer buffer = new StringBuffer(); // 버퍼로 이메일 보내는 내용을 적습니다.

			buffer.append("회원님의 비밀번호가 : "); // buffer.append("보내는사람 : ");
			buffer.append(password); //
			buffer.append("로 변경 되었습니다."); //

			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);

			Address toAddr = new InternetAddress(email);
			msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

			msg.setContent(buffer.toString(), "text/html;charset=UTF-8"); // 내용
			Transport.send(msg); // 전송
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (check == 0) {
			out.println("<script>");
			out.println("alert('잘못된 값을 입력하셨습니다.');");
			out.println("</script>");
			out.flush();
			out.close();
			return "login";
		} else {
			out.println("<script>");
			out.println("alert('비밀번호 변경 완료');");
			out.println("</script>");
			out.flush();
			out.close();
			return "login";
		}
	}
	/////////////////////////////////////////////////

	// pw_find.jsp
	// 비밀번호 변경 페이지
	@GetMapping("/pw_find")
	public String pwFindGET() {

		return "pw_find";
	}
	/////////////////////////////////////////////////

	// register_control
	// 회원가입 수행 컨트롤러
	@PostMapping("/register_control")
	public String registerControlPOST(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String password_confirm = request.getParameter("password_confirm");

		UserVO new_account = new UserVO(email, nickname, password);
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();

		if (password.equals(password_confirm)) {
			userService.insertUser(new_account);
			out.println("<script>");
			out.println("alert('회원가입을 완료하였습니다');");
			out.println("location.href='/';");
			out.println("</script>");
			out.flush();
			out.close();
			session.setAttribute("user_email", email);
			session.setAttribute("user_password", password);
			return "index";
		} else {
			out.println("<script>");
			out.println("alert('입력한 비밀번호가 일치하지 않습니다');");
			out.println("</script>");
			out.flush();
			out.close();
			return "register";
		}

	}
	/////////////////////////////////////////////////

	// register.jsp
	// 회원가입페이지 뷰
	@GetMapping("/register")
	public String registerGET() {
		return "register";
	}
	/////////////////////////////////////////////////

	// nav.jsp
	// 네비게이션  뷰
	@GetMapping("/nav")
	public String navGET(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String login = (String) session.getAttribute("session_user_email");
		String password = (String) session.getAttribute("session_user_password");
		String nick = (String) session.getAttribute("session_user_nick");
		UserVO vo = userService.getUserInfo(login);

		model.addAttribute(vo);
		return "nav";
	}

	/////////////////////////////////////////////////

}
