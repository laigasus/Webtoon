<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="com.board.model.*" %>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%
	request.setCharacterEncoding("utf-8"); 
	String email = (String)session.getAttribute("session_user_email");
	String writer = (String)session.getAttribute("session_user_nick");
	boolean check;
	if(email==null){
		check=false;
	}else{
		check=true;
	}
	String path = request.getSession().getServletContext().getRealPath("fileFolder");
	int size = 1024 * 1024 * 10; //저장가능한 파일 크기
	String file = ""; //업로드한 파일 이름(변경될 수 있음)
	String originalFile=""; //이름이 변경되었다면 변경되기 전 실제 파일 이름
	if(check){
		BoardDAO dao = BoardDAO.getInstance();
		MultipartRequest multi = new MultipartRequest(request,path,size,"utf-8",new DefaultFileRenamePolicy());
		Enumeration<?> files = multi.getFileNames();
		String str = null; //파일 이름을 받아와 저장
		file = multi.getFilesystemName(str); //업로드된 파일 이름 가져옴
		originalFile = multi.getOriginalFileName(str); // 원래 파일 이름 가져옴
		String absoluteImgPath = null; //path+'\\'+file; 이미지 업로드 기능 확장시 사용
		String title = multi.getParameter("title");//제목 가져옴
		String content = multi.getParameter("content");//본문 가져옴
		dao.regist(email,writer,title,content,absoluteImgPath);
	}
	else{
%>	<script>
		alert("로그인해주세요");
		location.href="login.jsp";
	</script>
<%	
	}
%>
<script>location.href='jajak.jsp'</script>
