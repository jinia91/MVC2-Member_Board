package dao;

import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.MemberDTO;

// 회원정보관련 모델 작동시 DB가 필요할경우, 모델 대신해 DB와 소통하는 객체
// 1. DAO 작동에 필요한 유틸메소드(커넥션 가져오기, 리소스 반환하기)
// 2. 회원가입을 위한 빈즈 DB에 전달

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
	// 커넥션풀에서 커넥션 가져오기
	public Connection getConnection() {
		InitialContext ic;
		Connection conn = null;

		try {
			ic = new InitialContext(); // jdni 객체 생성 -

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle"); // 오라클 리소스를 찾아서 객체반환
			conn = ds.getConnection(); // 리소스 내의 커넥션 획득

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("커넥션 획득 오류" + e);
		}

		return conn;

	}

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
	public void memberJoin(MemberDTO member) {
		Connection conn = null;
		PreparedStatement psmt = null;

		conn = getConnection();
		try {
			psmt = conn.prepareStatement("insert into member values(?,?,?,?,?)");
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPwd());
			psmt.setString(3, member.getEmail());
			psmt.setString(4, member.getBirth());
			psmt.setString(5, member.getGender());
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터 베이스 저장 오류" +e);
		}
		finally {
			close(conn,psmt);
		}
	}
	

}
