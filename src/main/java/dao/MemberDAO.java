package dao;

import java.sql.*;
import beans.MemberDTO;

// ȸ���������� �� �۵��� DB�� �ʿ��Ұ��, �� ����� DB�� �����ϴ� ��ü
// 1. DAO �۵��� �ʿ��� ��ƿ�޼ҵ�(���ҽ� ��ȯ�ϱ�)
// 2. ȸ�������� ���� ���� DB�� ����
// 3. �α����� ���� ������ �񱳴��� �Ǻ�

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

	// ��ȯ 2
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

}
