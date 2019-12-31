<%@page import="kr.co.stephen.board.model.Board"%>
<%@page import="kr.co.stephen.board.model.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//BoardDAO 클래스의 selectAll()를 호출하여
	//DB에 들어있는 모든 글을 리스트로 반환 받아야 합니다.
	List<Board> articles = BoardDAO.getInstance().selectAll();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  게시물이 없다면 -->
	<%if(articles.size()<=0){%>
	<p>게시물이 존지하지 않습니다.</p>
	<%}else{ %>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>비고</th>
		</tr>
		<%for(Board board : articles){%>
		<tr>
			<td><%=board.getBoardId() %></td>
			<td><%=board.getWriter() %></td>
			<td><a href="content.jsp?id=<%= board.getBoardId()%>"><%=board.getTitle()%></a></td>
			<td>
			<a href = "delete_con.jsp?id=<%=board.getBoardId()%>">[삭제]</a>
			</td>
		</tr>
		<%} %>
	<!-- selectAll() 결과값으로 리턴받은 리스트를 반복문을  사용하여 하나씩 테이블에 출력해 주시면 됩니다.-->
	</table>
	<%} %>
<br>
<p>
	<a href = "write.jsp">게시글 작성하기</a>
</body>
</html>