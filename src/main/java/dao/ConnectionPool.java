package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


// Ŀ�ؼ�Ǯ���� Ŀ�ؼ��� ������ ������ jdni��ü ������ ���ҽ��� ���̹�
// ���ֵ̹� dbms���ҽ��� ������� Ŀ�ؼ�Ǯ(�����ϴ� datasource) ��ü ��ȯ����
// ���⼭ Ŀ�ؼ��� ������
// �������� �ݺ��� ���� �и��Ͽ� �̱������� �������μ� �ݺ��� ������
// Ŀ�ؼ� �������� �޼ҵ常 ȣ���ϰ��ϴ°� ���ɻ� �� �������� �� �𸣰��� �̱�������, �������, ���߾�����, ��Ʈ��ũ ���ΰ� �ʿ���


public class ConnectionPool {  // jdni ���� �� Ŀ�ؼ�Ǯ ���� ��ü

	// �̱��� ����
	private static ConnectionPool cp = new ConnectionPool();
	private DataSource ds;  
	
	private ConnectionPool() {  // 1ȸ ���� ������
		InitialContext ic;
		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle"); // ����Ŭ ���ҽ��� ã�Ƽ� ��ü��ȯ

		} catch (NamingException e) {
			System.out.println("jdni ��ü ���� ����" + e);
		}
	}

	public static ConnectionPool getInstance() {
		return cp;
	}
	// �̱��� ����
	
	
	public Connection getConnection() {
		
		Connection  conn = null;
		
		try {
			conn = cp.ds.getConnection();
		} catch (SQLException e) {
			System.out.println("Ŀ�ؼ� ȹ�� ����" + e);
		}

		return conn;
		
	}

		
}
