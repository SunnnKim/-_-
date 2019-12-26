package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

// Model 1: javabean이용
// MemberDao
// DB <-> java 연결해주는 역할
public class MemberDao {
	// model 1 
	// view -> model (dao) + controller -> 
	// DAO : 디비와 연결되어서 데이터 꺼내오는 역할을 함 (javabean)
	// javabean : controller 역할을 함
	// 1) 개발속도가 빠르다 (controller가 없음)
	
	
	// model 2
	// main -> controller + dao -> view (맨처음에는 뷰) -> 	
	// dao - view는 직접적인 연결을 잘 안함
	// dao <-> DB: dao에서 디비로 연결해서 데이터 가져오고 controller 에서 원하는 처리를 하고
	// view로 전달, view에서 출력한 다음에는 다시 controller로 다음 처리를 실행, 반복함
	// spring 방식
	
	
	// MemberDao는 
	// 어디서나 접근 가능하도록 싱글턴으로 만든다
	private static MemberDao mem = null;
	private MemberDto loginUser;
	private String loginId;
	

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public MemberDto getLoginUser() {
		return loginUser;
	}

	

	public MemberDao() {
	}
	
	public static MemberDao getInstance() {
		if(mem == null ) {
			mem = new MemberDao();
		}
		return mem;
	}
	
	// join 
	public boolean addMember(MemberDto dto) {
		
		// DB에 Member를 추가하는 작업
	 
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
	// 로그인
	public  MemberDto login(String id, String pwd) {
			String sql = " SELECT ID, NAME, EMAIL, AUTH "
					+ " FROM MEMBER "
					+ " WHERE ID = ? AND PWD = ?";
			
			System.out.println("sql : " + sql);

			
			// 기본셋팅
			PreparedStatement psmt = null;
			Connection conn = null;
			ResultSet rs = null;
		
			MemberDto loginUser = null;
			
			try {
				
				conn = DBConnection.getConnection();
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,id);
				psmt.setString(2,pwd);
				
				
				// 쿼리 실행하기
				rs = psmt.executeQuery();
				// 업데이트여부 count변수에 담기 
				if(rs.next()) {
					String _id = rs.getString(1);
					String _name = rs.getString(2);
					String _email= rs.getString(3);
					int auth= rs.getInt(4);
					loginUser = new MemberDto(_id, null, _name, _email, auth);
				}
				
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				DBClose.close(psmt, conn, rs);
				
			}
		this.loginUser = loginUser;
		return loginUser;
	
}
	
	
	
	// 아이디 정규화방식
	
	
	
	
	
	
	
	
	
	
	
	
	// 아이디 있는지 확인하기 ( 회원가입 아이디 중복확인 )
	public boolean getId(String id) {
		String sql = " SELECT COUNT(*) "
					+ " FROM MEMBER "
					+ " WHERE ID = ? ";
		
		Connection conn = null;	// DB CONNECTION 
		PreparedStatement psmt = null;	// SQL
		ResultSet rs = null;			// RESULT
		
		boolean findid = false;
		System.out.println("sql : " + sql ); 	// SQL 확인		
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			// 아이디가 있으면 true 반환
			int count = 0;
			if(rs.next()) count = rs.getInt("COUNT(*)");
			System.out.println("count: " + count);
			System.out.println("findid: "+findid);
			if(count > 0) findid = true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, rs);
			
		}
		
		return findid;
	
	}

	
	
}
