package kr.co.stephen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcSelect {

	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";
		String uid = "jsp"; //root
		String upw = "jsp"; //mysql

		String sql = "SELECT * FROM members";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select문에서만 사용하는 객체.
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =  DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			/*
			 * -SELECT 쿼리문의 실행 결과 데이터가 한 줄이라도 존재한다면
			 * rs.next()메서드에는  true를 리턴하고, 해당 행을 지목합니다.
			 */
			System.out.println("------------------------------");
			
			while(rs.next()) {
				/*
				 * SELECT의 실행 결과의 컬럼값을 읽어오려면
				 * getString(), getInt()등의 메서드를 사용합니다.
				 * -해당 메서드의 메게값으로 읽어올 테이블의 컬러명을 지정하니다.
				 */
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				System.out.printf("# 아이디 : %s \n # 비밀번호 : %s\n # 이름 : %s \n # 이메일 : %s\n", id, pw, name, email);
				
				System.out.println("-------------------------");
			}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			conn.close(); pstmt.close(); rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	}
}
