package javabean;

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
}
