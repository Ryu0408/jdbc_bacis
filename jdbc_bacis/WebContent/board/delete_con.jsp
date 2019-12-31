<%@page import="kr.co.stephen.board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Long id = Long.parseLong(request.getParameter("id"));
	boolean flag = BoardDAO.getInstance().delete(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	alert("선택하신 글이 삭제되었습니다.");
	location.href="list.jsp"; // 원하는 jsp페이지로 이동
	</script>
</body>
</html>