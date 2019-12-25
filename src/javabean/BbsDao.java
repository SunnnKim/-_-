package javabean;
/*CREATE TABLE BBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(30) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL
);
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;

public class BbsDao {
	
	private static BbsDao dao = new BbsDao();
	
	private BbsDao() {
		// TODO Auto-generated constructor stub
	}
		

	public static BbsDao getInstance() {
		
		return dao;
	}
	
	// 게시판에 모든 글 뿌려주기  
	public List<BbsDto> getBbsList(){
		String sql =  " SELECT SEQ, ID, TITLE, CONTENT, "
					+ " WDATE, DEL, READCOUNT "
					+ " FROM BBS "
					+ " ORDER BY WDATE DESC ";
		
		Connection conn = null;	// DB CONNECTION 
		PreparedStatement psmt = null;	// SQL
		ResultSet rs = null;			// RESULT
		
		System.out.println("sql : " + sql ); 	// SQL 확인		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();	// select 는 executeQuery, 나머지는 executeUpdate
			
			while(rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1),//seq, 
										rs.getString(2),//id,
										rs.getString(3),//title, 
										rs.getString(4),//contents, 
										rs.getString(5),//wdate, 
										rs.getInt(6),//del, 
										rs.getInt(7));//readcount
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
		
	}
	
	
	// 새로 쓸 글 디비에 추가하기 
	public boolean wirteBbs(String id, String title, String content) {
		boolean write = false;
		/*
		 SEQ NUMBER(8) PRIMARY KEY,
		ID VARCHAR2(30) NOT NULL,
		TITLE VARCHAR2(200) NOT NULL,
		CONTENT VARCHAR2(4000) NOT NULL,
		WDATE DATE NOT NULL,
		DEL NUMBER(1) NOT NULL,
		READCOUNT NUMBER(8) NOT NULL
		 */
		
		String sql =  " INSERT INTO BBS ( SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT ) "
					+ " VALUES ( SEQ_BBS.NEXTVAL, ?, ?, ?, SYSDATE, 0, 0 ) ";
		
		System.out.println("sql : " + sql);

		
		// 기본셋팅
		PreparedStatement psmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		
		try {
			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, content);
			
			
			count = psmt.executeUpdate();
			
			if(count>0) {
				write = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, rs);
		}
		
		
		
		
		
		return write;
	}
}
