package dao;

import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.MemberDTO;

// ȸ���������� �� �۵��� DB�� �ʿ��Ұ��, �� ����� DB�� �����ϴ� ��ü
// 1. DAO �۵��� �ʿ��� ��ƿ�޼ҵ�(Ŀ�ؼ� ��������, ���ҽ� ��ȯ�ϱ�)
// 2. ȸ�������� ���� ���� DB�� ����

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
	// Ŀ�ؼ�Ǯ���� Ŀ�ؼ� ��������
	public Connection getConnection() {
		InitialContext ic;
		Connection conn = null;

		try {
			ic = new InitialContext(); // jdni ��ü ���� -

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle"); // ����Ŭ ���ҽ��� ã�Ƽ� ��ü��ȯ
			conn = ds.getConnection(); // ���ҽ� ���� Ŀ�ؼ� ȹ��

		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Ŀ�ؼ� ȹ�� ����" + e);
		}

		return conn;

	}

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
			System.out.println("������ ���̽� ���� ����" +e);
		}
		finally {
			close(conn,psmt);
		}
	}
	

}
