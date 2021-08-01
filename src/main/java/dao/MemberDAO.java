package dao;

import java.sql.*;
import beans.MemberDTO;

// ȸ���������� �� �۵��� DB�� �ʿ��Ұ��, �� ����� DB�� �����ϴ� ��ü
// 1. DAO �۵��� �ʿ��� ��ƿ�޼ҵ�(���ҽ� ��ȯ�ϱ�)
// 2. ȸ�������� ���� ���� DB�� ����
// 3. �α����� ���� ������ �񱳴��� �Ǻ�
// 4. ȸ�� ���� ã�� 
// 5. ȸ�� ���� ��ü �ҷ�����
// 6. ȸ�� ���� �����ϱ�
// 7. ȸ�� ���� �����ϱ�

public class MemberDAO {

	// �̱��� ����
	private static MemberDAO dao = new MemberDAO();

	private MemberDAO() {
	};

	public static MemberDAO getInstance() {

		return dao;
	}
	// �̱��� ����

	
	// 1. ��ƿ�޼ҵ�
	// ���ҽ� ��ȯ
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("sql ��ȯ��� �ݱ� ����" + e);
			}

		}
		close(conn, ps);

	}

	// ���ҽ� ��ȯ (2)
	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {

			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ps �ݱ� ����" + e);
			}

		}
		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Ŀ�ؼ� �ݱ� ����" + e);
			}
		}

	}

	// 2. ȸ�� ���� ���� DB ����
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
			System.out.println("������ ���̽� ���� ����" + e);
		} finally {
			close(conn, psmt);
		}
	}

	// 3. �α��� �� ����
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
			System.out.println("�α��� ���� �߻�" + e);
		} finally {
			close(conn,psmt, rs);
		}
		return -1;

	}
	
	// 4. ȸ�� ��й�ȣ ã��
	public String memberSearch(String id, String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		
		String pwd = null;				// ȸ���� ������ pwd ��ȯ , ������ "" ��ȯ
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
			System.out.println("ȸ��ã�� ���� �߻�" + e);
		}finally {
			close(conn,psmt, rs);
		}
		
		return pwd;
		
	}
	
	
	// 5. ȸ������ ���� �ҷ�����
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
			System.out.println("ȸ������ ���� �ҷ����� ���� �߻�" + e);
		}finally {
			close(conn,psmt, rs);
		}
		return member;
		
	}
	
	
	// 6. ȸ������ db ����
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
			System.out.println("������ ���̽� ���� ����" + e);
		} finally {
			close(conn, psmt);
		}
	}

	// 7. ȸ������ db ����
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
				System.out.println("������ ���̽� ���� ����" + e);
			} finally {
				close(conn, psmt);
			}
		}
	

}
