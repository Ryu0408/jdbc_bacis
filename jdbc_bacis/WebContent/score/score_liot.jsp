<%@page import="kr.co.stephen.score.model.Scores"%>
<%@page import="kr.co.stephen.score.model.ScoreDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Scores> scoreList = ScoreDAO.getInstance().list();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type = "text/css">
	.del-btn{
		display: inline-block;
		text-decoration: none;
		color: white;
		background: red;
		border: 1px solid black;
	}
	.del-btn:hover{
		background: blue;
	}
</style>
</head>
<body>
	<h1>학생들의 전체 성적 조회</h1>
	<form action = "serch.jsp" method = "post">
		검색 <input type = "text" name = "keyword" placeholder="검색할 이름 입력">
		 <input type = "submit" value = "확인">
	</form>
	<br>
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
			<td><a class = "del-btn" href = "delete.jsp?id=<%=s.getId()%>">삭제</a> </td>
		</tr>
		<%} %>
	</table>
</body>
</html>