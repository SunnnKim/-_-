package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class InsertClass {
	
	
	
	// DB와 연결하여 연결
	public InsertClass() {
	}

	
	
	// 추가하는 함수
	public boolean insertMember(MemberDto dto) {
		String id = dto.getId();
		String pwd = dto.getPwd();
		String name = dto.getName();
		String email = dto.getEmail();
		int auth = dto.getAuth();

		
		// AUTH : 관리자 0, 유저 1 
		String sql = " INSERT INTO MEMBER ( ID, PWD, NAME, EMAIL, AUTH ) " +
			 " VALUES ( ?, ?, ?, ?, ? ) ";
		
		System.out.println("sql : " + sql);
		
		// 기본셋팅
		PreparedStatement psmt = null;
		Connection conn = null;
		ResultSet rs = null;
		boolean b =false;
		
		

		try {
			
			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);
			psmt.setString(2,pwd);
			psmt.setString(3,name);
			psmt.setString(4,email);
			psmt.setInt(5,auth);
			
			// 쿼리 실행하기
			rs = psmt.executeQuery();
			// 업데이트여부 count변수에 담기 
			if(rs.next()) b =true;
			
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			DBClose.close(psmt, conn, rs);
			
		}
		
		
		return b;
		
	}
	
	
	
}
