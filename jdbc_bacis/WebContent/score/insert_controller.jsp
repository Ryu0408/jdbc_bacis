<%@page import="kr.co.stephen.score.model.IScoreDAO"%>
<%@page import="kr.co.stephen.score.model.ScoreDAO"%>
<%@page import="kr.co.stephen.score.model.Scores"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  request.setCharacterEncoding("UTF-8");
	Scores scores = new Scores();
	scores.setName(request.getParameter("name"));
	scores.setKor(Integer.parseInt(request.getParameter("kor")));
	scores.setEng(Integer.parseInt(request.getParameter("eng")));
	scores.setMath(Integer.parseInt(request.getParameter("math")));
	scores.setTotal();
	scores.setAverage();
	
	IScoreDAO dao = ScoreDAO.getInstance();
	if(dao.insert(scores)){
		response.sendRedirect("insert_success.jsp");
	}else{
		response.sendRedirect("Insert_form.jsp");
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>