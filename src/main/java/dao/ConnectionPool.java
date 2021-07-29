package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


// 커넥션풀에서 커넥션을 얻어오는 과정은 jdni객체 생성후 리소스에 네이밍
// 네이밍된 dbms리소스에 만들어진 커넥션풀(관리하는 datasource) 객체 반환받음
// 여기서 커넥션을 가져옴
// 본과정의 반복을 별도 분리하여 싱글톤으로 만듬으로서 반복성 제거함
// 커넥션 가져오는 메소드만 호출하게하는게 성능상 더 좋은건지 잘 모르겠음 싱글톤패턴, 멤버변수, 다중쓰레드, 네트워크 공부가 필요함


public class ConnectionPool {  // jdni 선언 및 커넥션풀 관리 객체

	// 싱글톤 설계
	private static ConnectionPool cp = new ConnectionPool();
	private DataSource ds;  
	
	private ConnectionPool() {  // 1회 생성 생성자
		InitialContext ic;
		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle"); // 오라클 리소스를 찾아서 객체반환

		} catch (NamingException e) {
			System.out.println("jdni 객체 생성 오류" + e);
		}
	}

	public static ConnectionPool getInstance() {
		return cp;
	}
	// 싱글톤 설계
	
	
	public Connection getConnection() {
		
		Connection  conn = null;
		
		try {
			conn = cp.ds.getConnection();
		} catch (SQLException e) {
			System.out.println("커넥션 획득 오류" + e);
		}

		return conn;
		
	}

		
}
