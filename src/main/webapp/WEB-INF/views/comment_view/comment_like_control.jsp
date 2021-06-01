<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="com.comment.model.*"%>

<%
	int bd_num=Integer.parseInt(request.getParameter("bd_num"));
	int cm_id=Integer.parseInt(request.getParameter("cm_id"));
	String writer=request.getParameter("writer");
	CommentDAO.getInstance().likeComment(bd_num, cm_id);
	
%>
<script> location.href="jajak_content.jsp?Bd_num=<%=bd_num%>&nick=<%=writer%>#comment-h1";</script>