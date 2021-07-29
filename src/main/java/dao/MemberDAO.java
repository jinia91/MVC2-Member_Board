package dao;

import java.sql.*;
import beans.MemberDTO;

// 회원정보관련 모델 작동시 DB가 필요할경우, 모델 대신해 DB와 소통하는 객체
// 1. DAO 작동에 필요한 유틸메소드(리소스 반환하기)
// 2. 회원가입을 위한 빈즈 DB에 전달
// 3. 로그인을 위한 데이터 비교대조 판별

public class MemberDAO {

	// 싱글톤 설계
	private static MemberDAO dao = new MemberDAO();

	private MemberDAO() {
	};

	public static MemberDAO getInstance() {

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

	// 반환 2
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

	// 2. 회원 가입 정보 DB 저장
	public void memberJoin(MemberDTO member){
		Connection conn = null;
		PreparedStatement psmt = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		
		conn = cp.getConnection();
		
		try {
			psmt = conn.prepareStatement("insert into member values(?,?,?,?,?)");
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPwd());
			psmt.setString(3, member.getEmail());
			psmt.setString(4, member.getBirth());
			psmt.setString(5, member.getGender());
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터 베이스 저장 오류" + e);
		} finally {
			close(conn, psmt);
		}
	}

	// 3. 로그인 비교 대조
	public int memberLogin(String id, String pwd){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		
		conn = cp.getConnection();
		
		try {
			psmt = conn.prepareStatement("select pwd from member where id = ?");
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) {
					return 1;
				} else
					return 0;
			}
		} catch (SQLException e) {
			System.out.println("로그인 오류 발생" + e);
		} finally {
			close(conn,psmt, rs);
		}
		return -1;

	}

}
