package kr.co.stephen.board.model;

import java.sql.Date;

/*CREATE TABLE board(
		board_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	    writer VARCHAR(45) NOT NULL,
	    title VARCHAR(100) NOT NULL,
	    content TEXT NULL,
	    created_at DATETIME DEFAULT NOW()
	);*/
public class Board {
	private Long boardId;
	private String writer;
	private String title;
	private String content;
	private Date createAt;

	public Board() {}

	public Board(Long boardId, String writer, String title, String content, Date createAt) {
		super();
		this.boardId = boardId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.createAt = createAt;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}

