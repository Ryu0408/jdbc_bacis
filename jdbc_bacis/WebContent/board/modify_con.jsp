<%@page import="kr.co.stephen.board.model.BoardDAO"%>
<%@page import="kr.co.stephen.board.model.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		-필요한 파라미터값을 불러와서 그것을 토대로
		Board객체를 생성해주세요.
		
		- update()를 이용하여 숮어한 객체를 DB에 넣어주시고
		결과가 성공이라면 해당 글 상세보기 페이지로 이동해주세요. (sendRedirect)
		결과가 실패라면 list.jsp로 리다이렉팅 해주세요
	*/

	request.setCharacterEncoding("UTF-8");

	Long boardId =Long.parseLong(request.getParameter("boardNo"));
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	Board article = new Board();
	
	article.setBoardId(boardId);
	article.setWriter(writer);
	article.setTitle(title);
	article.setContent(content);
	
	boolean flag = BoardDAO.getInstance().update(article);
	
	if(flag)response.sendRedirect("content.jsp?id=" + boardId);
	else response.sendRedirect("list.jsp");

%>