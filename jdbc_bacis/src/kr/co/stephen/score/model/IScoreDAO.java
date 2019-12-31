package kr.co.stephen.score.model;

import java.util.List;

public interface IScoreDAO {
	//점수등록
	boolean insert(Scores score);
	
	//점수조회기능
	List<Scores> list();
	
	//검색 기능
	List<Scores> serch(String keyword);
	
	//삭제 기능
	boolean delete(Long id);
	
}