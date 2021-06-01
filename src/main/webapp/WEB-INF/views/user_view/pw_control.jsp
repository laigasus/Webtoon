<%@page import="java.util.*"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="com.external.module.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.user.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% 
	String email = request.getParameter("email"); 
	String session_user_email=(String)session.getAttribute("session_user_email");
	UserDAO dao = UserDAO.getInstance();
	
	int userCheck = dao.userCheckEmail(email);
	if(session_user_email==null){  //로그인중 비번을 몰라서 왔을때
		if(userCheck==1){  //이메일일치
			out.println("<script>");
			out.println("alert('회원님의 임시비밀번호가 이메일로 보내졌습니다.');");
			out.println("</script>");
		}else {  //userCheck -1 받았던가 걍 실패
			out.println("<script>");
			out.println("alert('유효하지 않은 이메일입니다.');");
			out.println("location.href = 'pw_find.jsp';");
			out.println("</script>");
		}
	}else{ //이미 로그인을 했을때 비번을 바꾸려고 할때
		if(session_user_email.equals(email)){
			out.println("<script>");
			out.println("alert('회원님의 임시비밀번호가 이메일로 보내졌습니다.');");
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('유효하지 않은 이메일입니다.');");
			out.println("location.href = 'pw_find.jsp';");
			out.println("</script>");
		}
	}

	
   String from = "laigasus98@gmail.com";//관리자 이메일 보낼 이메일  laigasus98@gmail.com
   Properties p = new Properties(); // 정보를 담을 객체
   
   p.put("mail.smtp.host","smtp.gmail.com"); 
   p.put("mail.smtp.port", "465");       //gmail은 port 465 사용  구글에서 네이버로 보내면 구글것만 있으면 되는거야
   p.put("mail.smtp.starttls.enable", "true");
   p.put("mail.smtp.auth", "true");
   p.put("mail.smtp.debug", "true");
   p.put("mail.smtp.socketFactory.port", "465");             //gmail은 port 465 사용
   p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
   p.put("mail.smtp.socketFactory.fallback", "false");
   
 %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title></title>
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </head>
  <body>
  <%
    DBConnect conn = new DBConnect();
    PreparedStatement pstmt = null;
	ResultSet rs = null;
	int check = 1;
	Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
    random.setSeed(System.currentTimeMillis()); //시드값 설정을 따로 할수도 있음 일단은 시간에 따라 변화

    String password=Integer.toString(random.nextInt(9999999));   //9999999이하 난수
    
	String sql = "update toon_user set pw=? where email=? ";   // email=?2 인 이메일에 비번을 pw ?1 로 수정  이떄 pw는 데이터베이스에 칼럼이름과 일치해야함
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,password);
		pstmt.setString(2, email);  
		pstmt.executeUpdate();
	} catch (SQLException e) {//오류시
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
	%>
	<%//여기부터는 이메일을 보내는 코드입니다.
	try{
	Authenticator auth = new SMTPAuthenticatior();
    Session ses = Session.getInstance(p, auth);
     
    ses.setDebug(true);
    MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체  

    msg.setSubject("툰스토어에서 임시비밀번호를 보냈습니다."); //  제목  받는 사람에게 가장먼저 보여질 제목입니다.

    StringBuffer buffer = new StringBuffer();  //버퍼로 이메일 보내는 내용을 적습니다.
	
    buffer.append("회원님의 비밀번호가 : ");  //buffer.append("보내는사람 : ");
    buffer.append(password);  //
    buffer.append("로 변경 되었습니다.");  //

	Address fromAddr = new InternetAddress(from);
	msg.setFrom(fromAddr);	
	
	Address toAddr = new InternetAddress(email);
	msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람 
	
	msg.setContent(buffer.toString(), "text/html;charset=UTF-8"); // 내용
	Transport.send(msg); // 전송   
	}catch(Exception e){
	    e.printStackTrace();
	    return;    
	}
    
	%>
	<% if(check==0) {%>
    <script>
      alert("잘못된 값을 입력하셨습니다.");
      location.href = "pw_find.jsp";
    </script>
    <%} else {%>
    <script>
      alert("비밀번호 변경 완료");
      location.href = "pw_after.jsp";
    </script>
    <% } %>
      </body>
</html>
