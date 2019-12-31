<%@page import="kr.co.stephen.score.model.Scores"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.stephen.score.model.ScoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String keyword = request.getParameter("keyword") + "%";
	
	List<Scores> scoreList = ScoreDAO.getInstance().serch(keyword);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%if(scoreList.size()>0){ %>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>비고</th>
		</tr>
		<%for (Scores s : scoreList){ %>
		<tr>
			<td> <%= s.getName() %></td> <!--  학생들의 점수를 DB에서 얻어와서 뿌려주는 공간. -->
			<td> <%= s.getKor() %></td> <!--  학생들의 점수를 DB에서 얻어와서 뿌려주는 공간. -->
			<td> <%= s.getEng() %></td> <!--  학생들의 점수를 DB에서 얻어와서 뿌려주는 공간. -->
			<td> <%= s.getMath() %></td> <!--  학생들의 점수를 DB에서 얻어와서 뿌려주는 공간. -->
			<td> <%= s.getTotal() %></td> <!--  학생들의 점수를 DB에서 얻어와서 뿌려주는 공간. -->
			<td> <%= s.getAverage() %></td> <!--  학생들의 점수를 DB에서 얻어와서 뿌려주는 공간. -->
		</tr>
		<%} %>
	</table>
	<% } else{%>
	<h2>검색결과가 없어요~~~</h2>
	<%} %>
	<a href = "Insert_form.jsp">다른 점수 등록하기</a>
	<a href = "score_liot.jsp">목록으로 돌아가기</a>
</body>
</html>