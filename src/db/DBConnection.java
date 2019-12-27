package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	
	public static void initConnection() {

		try {
			// 클래스가 있는지 확인하는 것 : 오라클 드라이버안에 클래스(연결해주는 역할)이 있는지 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!");
			// 따로 연결하지 않으면 예외뜸

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	// Connect가 되었을 때 정보를 가지고 있는 클래스 생성
	public static Connection getConnection() {
		Connection conn = null;
		// 시스템계정 연결한 폴더로 이동해서(perspective를 db로) 오라클 계정 디비 우클릭 -> 속성으로 가서 Drivers
		// properties
		// -> Connection url주소 복사해서 가져오기
		try {
			// ip 192.168.2.28
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		// 맥용 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("DB Connection Success!");
		} catch (SQLException e) {
			// DB 활성화하지 않으면 예외뜸
			e.printStackTrace();
		}

		return conn;

	}
	
	
	
}
