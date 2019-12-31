package kr.co.stephen.jdbc;

import java.sql.*;
import java.util.*;

public class MembersManager {

	private static Scanner sc = new Scanner(System.in);
	private static Connection conn;

	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public static void main(String[] args) {
		while(true) {
			System.out.println("\n### 회원 관리 프로그램");
			System.out.println("# 1.회원 정보 등록하기");
			System.out.println("# 2.전체 회원 정보 조회하기");
			System.out.println("# 3.개별 회원 정보 조회하기");
			System.out.println("# 4.회원 정보 삭제하기");
			System.out.println("# 5.프로그램 종료");
			System.out.println("#메뉴를 입력하세요:");
			int  menu = sc.nextInt();
			
			switch(menu) {
			
			case 1:
				insert();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				selectOne();
				break;
			case 4:
				delete();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				System.exit(0);
				break;
				
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
			}
		}
	}
	// 커넥션 객체를 제공하는 메서드
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";
		String uid = "jsp";
		String upw = "jsp";
		
		Class.forName(driverName);
		
		return DriverManager.getConnection(url, uid, upw);
	}
	
	private static void insert() {
		System.out.println("# 회원 정보를 입력하세요.");
		
		System.out.println(" 아이디 : ");
		String id = sc.next();
		
		System.out.println(" 비밀번호 : ");
		String pw= sc.next();
		
		System.out.println(" 이름 : ");
		String name = sc.next();
		
		System.out.println(" 이메일 : ");
		String email = sc.next();
		
		String sql = "INSERT INTO members VALUES(?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
		
			int rn = pstmt.executeUpdate();
		
			if(rn==1) {
				System.out.println("회원 정보 저장이 완료되었습니다.");
			}else {
				System.out.println("회원 정보 저장에 실패했습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close(); pstmt.close(); 
			}catch(Exception e) {
				e.printStackTrace();
				}
		}
	}
	
	private static void selectAll() {
		System.out.println("#전체 회원 정보입니다.");
		String sql = "SELECT * FROM members ORDER BY name ASC";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int i=0;
			while(rs.next()){
				i++;
				System.out.println(i + "번째 회원 정보입니다!");
				System.out.printf("# 아이디(%s), 비밀번호(%s#), 이름(%s), 이메일(%s)\n", 
						rs.getString("id"), rs.getString("pw"), rs.getString("name"),rs.getString("email"));
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
	
	private static void selectOne() {
		System.out.println("#확인하고자 하는 회원 아이디를 입력하세요.");
		System.out.println(" 아이디 : ");
		String id = sc.next();
		String sql = "SELECT * FROM members WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);	
			rs = pstmt.executeQuery();
			
			if(!rs.next()) System.out.println("입력하신 ID는 없습니다.");
			while(rs.next()){
				System.out.println("입력하신 " + id +"의회원 정보입니다!");
				System.out.printf("# 아이디(%s), 비밀번호(%s#), 이름(%s), 이메일(%s)\n", 
						rs.getString("id"), rs.getString("pw"), rs.getString("name"),rs.getString("email"));
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

	private static void delete() {
		String sql = "DELETE FROM member WHERE id=?";
		System.out.println("삭제할 회원의 ID를 입력하세요");
		String id = sc.next();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int rn = pstmt.executeUpdate();
			
			if(rn==1) {
				System.out.println("회원 ID:" + id + "이(가) 정상 삭제되었습니다.");
			}
			else {
				System.out.println("DB에 없는 회원입니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close(); pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				}
		}
	}
}