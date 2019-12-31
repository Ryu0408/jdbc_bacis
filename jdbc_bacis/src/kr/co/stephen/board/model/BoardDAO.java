package kr.co.stephen.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class BoardDAO implements IBoardDAO {

	//싱글톤 패턴
	//1. 생성자에 private 제한
	private BoardDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//2. 클래스 내부에서 자신 스스로의 객체를 단 1개만 생성
	private static BoardDAO boardDAO = new BoardDAO();
	
	//3. 외부에서 객체 요구 시 공개된 메서드를 통해 주소값 리턴
	public static BoardDAO getInstance() {
		if(boardDAO==null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	//커넥션 객체를 생성하여 리턴해주z
	private Connection getConnection() throws Exception{
		String url = "jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";
		String uid = "jsp";
		String upw = "jsp";
		
		return DriverManager.getConnection(url,uid,upw);
		
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	@Override
	public boolean insert(Board article) {
		boolean flag = false;
		
		String sql = "INSERT INTO board"
				+ "(writer, title, content)"
				+ "VALUES (?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			
			if(pstmt.executeUpdate()==1)
				flag = true;
			else flag = false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!= null)pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public Board selectOne(Long boardId) {
		
		String sql = "SELECT * FROM board WHERE board_id=?";
		Board article = null;
		try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, boardId);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			article = new Board(rs.getLong("board_id"),
								rs.getString("writer"),
								rs.getString("title"),
								rs.getString("content"),
								rs.getDate("created_at")
					);
					
		
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return article;
	}

	@Override
	public List<Board> selectAll() {
		
		String sql = "SELECT * FROM board ORDER BY board_id DESC";
		
		List<Board> articles = new ArrayList<>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board article = new Board(
						rs.getLong("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("created_at"));
				articles.add(article);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn!=null) conn.close();
					if(pstmt != null) pstmt.close();
					if(rs!=null)rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			

		return articles;
	}


	@Override
	public boolean update(Board article) {
		
		String sql = "UPDATE board "
					+"SET writer=?, title=?, content=? "
					+"WHERE board_id = ?";
		boolean flag = false;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			pstmt.setLong(4, article.getBoardId());
			
			if(pstmt.executeUpdate()==1)
				flag = true;
			else flag = false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!= null)pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean delete(Long boardId) {
		String sql = "DELETE FROM board WHERE board_id=?";
		boolean flag = false;
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, boardId);
		
		if(pstmt.executeUpdate()==1)
			flag = true;
		else flag = false;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(conn!=null)conn.close();
			if(pstmt!= null)pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return flag;
}

}
