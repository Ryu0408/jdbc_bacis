<%@page import="kr.co.stephen.score.model.ScoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Long id = Long.parseLong(request.getParameter("id"));
	
	boolean delete = ScoreDAO.getInstance().delete(id);
	
	//삭제 여부에 상관없이 score_list.jsp로 리다이렉팅 해주세요.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(delete){%>
		<h1>삭제되었습니다.</h1>
		<a href = "score_liot.jsp">점수조회리스트로 이동</a>			
	<%}%>
</body>
</html>