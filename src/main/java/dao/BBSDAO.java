package dao;

import java.sql.*;
import java.util.ArrayList;

import beans.BBSDTO;

// 게시판 관련 DB 접근을 담당하는 DAO

public class BBSDAO {
	// 싱글톤 설계
	private static BBSDAO dao = new BBSDAO();

	private BBSDAO() {
	};

	public static BBSDAO getInstance() {

		return dao;
	}
	// 싱글톤 설계

	
	// 1. 유틸메소드
	// 리소스 반환
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("sql 반환결과 닫기 오류" + e);
			}

		}
		close(conn, ps);

	}

	// 리소스 반환 (2)
	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {

			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ps 닫기 오류" + e);
			}

		}
		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("커넥션 닫기 오류" + e);
			}
		}

	}
	
	//1. 게시물 전체를 출력해주는 메소드
	public ArrayList<BBSDTO> BBSallList() throws SQLException{
		Connection conn = null;
		Statement smt = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		ResultSet rs = null;
		
		ArrayList<BBSDTO> list = new ArrayList<>();
		
		try {
		conn = cp.getConnection();
		smt = conn.createStatement();
			rs = smt.executeQuery("select * from BBS order by bbsid desc");
		
			while(rs.next()) {
				BBSDTO dto = new BBSDTO();
				dto.setBbsId(rs.getInt("BBSID"));
				dto.setBbsTitle(rs.getString("BBSTITLE"));
				dto.setBbsContent(rs.getString("BBSCONTENT"));
				dto.setBbsDate(rs.getTimestamp("BBSDATE"));
				dto.setBbsHit(rs.getInt("BBSHIT"));
				dto.setId(rs.getNString("ID"));
			
				list.add(dto);
			
			}
			
		
		} catch (SQLException e) {
			
			System.out.println("전체 게시물 불러오기 오류" + e);
		}finally {
			rs.close();
			smt.close();
			conn.close();
		}
		
		return list;
		
	}
	
	
	public void BBSWrite(BBSDTO dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		
		conn = cp.getConnection();
		try {
			psmt = conn.prepareStatement("insert into BBS(bbsid, bbstitle, bbscontent, id) "
					+ "values(BBS_seq.nextval,?,?,?)");
		
			psmt.setString(1, dto.getBbsTitle());
			psmt.setString(2, dto.getBbsContent());
			psmt.setString(3, dto.getId());
		
			psmt.executeUpdate();
		
		
		} catch (SQLException e) {
			System.out.println("게시물 작성 오류" + e);
		}
		
		
	}

}
