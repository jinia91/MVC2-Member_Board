package dao;

import java.sql.*;
import beans.MemberDTO;

// 회원정보관련 모델 작동시 DB가 필요할경우, 모델 대신해 DB와 소통하는 객체
// 1. DAO 작동에 필요한 유틸메소드(리소스 반환하기)
// 2. 회원가입을 위한 빈즈 DB에 전달
// 3. 로그인을 위한 데이터 비교대조 판별
// 4. 회원 정보 찾기 
// 5. 회원 정보 전체 불러오기
// 6. 회원 정보 수정하기
// 7. 회원 정보 삭제하기

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
	
	// 4. 회원 비밀번호 찾기
	public String memberSearch(String id, String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		
		String pwd = null;				// 회원이 있으면 pwd 반환 , 없으면 "" 반환
		conn = cp.getConnection();
		
		try {
			psmt = conn.prepareStatement("select pwd from member where id=? and email=?");
			psmt.setString(1, id);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString(1);
			}
		
		} catch (SQLException e) {
			System.out.println("회원찾기 오류 발생" + e);
		}finally {
			close(conn,psmt, rs);
		}
		
		return pwd;
		
	}
	
	
	// 5. 회원정보 전부 불러오기
	public MemberDTO memberSearchAll(String id, String pwd) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		MemberDTO member = new MemberDTO();
		
		conn = cp.getConnection();
		
		try {
			psmt = conn.prepareStatement("select * from member where id=? and pwd=?");
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				member.setId(rs.getString(1));
				member.setPwd(rs.getString(2));
				member.setEmail(rs.getString(3));
				member.setBirth(rs.getString(4));
				member.setGender(rs.getString(5));
			}
		
		} catch (SQLException e) {
			System.out.println("회원정보 전부 불러오기 오류 발생" + e);
		}finally {
			close(conn,psmt, rs);
		}
		return member;
		
	}
	
	
	// 6. 회원정보 db 수정
	public void memberUpdate(MemberDTO member){
		Connection conn = null;
		PreparedStatement psmt = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		
		conn = cp.getConnection();
		
		try {
			psmt = conn.prepareStatement("update member set pwd=?,email=?,birth=? where id=?");
			psmt.setString(1, member.getPwd());
			psmt.setString(2, member.getEmail());
			psmt.setString(3, member.getBirth());
			psmt.setString(4, member.getId());
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터 베이스 수정 오류" + e);
		} finally {
			close(conn, psmt);
		}
	}

	// 7. 회원정보 db 삭제
		public void memberDelete(String id, String pwd){
			Connection conn = null;
			PreparedStatement psmt = null;
			ConnectionPool cp = ConnectionPool.getInstance();
			
			conn = cp.getConnection();
			
			try {
				psmt = conn.prepareStatement("delete from member where id=? and pwd = ?");
				psmt.setString(1, id);
				psmt.setString(2, pwd);
				psmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("데이터 베이스 삭제 오류" + e);
			} finally {
				close(conn, psmt);
			}
		}
	

}
