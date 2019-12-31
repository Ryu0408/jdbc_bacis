<%@page import="kr.co.stephen.board.model.BoardDAO"%>
<%@page import="kr.co.stephen.board.model.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Long id = Long.parseLong(request.getParameter("id"));
	Board article = BoardDAO.getInstance().selectOne(id);
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1><%= article.getBoardId() %>번 게시글 내용</h1>

<p>
	# 글번호: <%= article.getBoardId() %><br>
	# 작성자: <%= article.getWriter()%><br>
	# 제목: <%=article.getTitle() %><br>
	# 내용:  <textarea rows="3" disabled><%= article.getContent() %></textarea><br>
</p>

<a href="list.jsp">글 목록보기</a>
<a href="modify.jsp?id=<%=article.getBoardId()%>">글 수정하기</a>
</body>
</html>