package kr.co.stephen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcSelect2 {

	public static void main(String[] args) {
		/* 회원의 ID를 입력받아 해당 ID의 회원정보를 모두 출력하는
		 * JDBC 프로그래밍 코드를 작성하세요.
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("조회할 회원의 ID를 입력하세요.");
		System.out.println(">");
		String userId = sc.next();
		
		String sql = "SELECT * FROM members WHERE id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select문에서만 사용하는 객체

		
		String uid = "jsp"; //root
		String upw = "jsp"; //mysql
		String url = "jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";

		try {
			//1. JTBC 커넥터 드라이버 호출.
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn =  DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			System.out.println("------------------------------");
			
			while(rs.next()) {
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
				conn.close(); pstmt.close(); rs.close(); sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		}
	}
