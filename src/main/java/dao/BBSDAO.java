package dao;

import java.sql.*;
import java.util.ArrayList;

import beans.BBSDTO;

// �Խ��� ���� DB ������ ����ϴ� DAO

public class BBSDAO {
	// �̱��� ����
	private static BBSDAO dao = new BBSDAO();

	private BBSDAO() {
	};

	public static BBSDAO getInstance() {

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
	
	//1. �Խù� ��ü�� ������ִ� �޼ҵ�
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
			
			System.out.println("��ü �Խù� �ҷ����� ����" + e);
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
			System.out.println("�Խù� �ۼ� ����" + e);
		}
		
		
	}

}
