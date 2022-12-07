package com.jsp.study.dao;

import java.sql.Connection;
import java.sql.*;

public class ScottDB {
	// 객체생성 없이 url user pw를 사용하고 싶다. 멤버변수로 등록
	// (static : JVM이 코드를 분석하면서 static으로 선언된 필드를 먼저 객체로 만들어서 메소드 영역에 저장)
	// ScottDB.url 
	// 다른 패키지에서 사용하고 싶으니 public 
	public static final String URL = "jdbc:mysql://localhost:3306/SCOTT";
	public static final String USER = "root";
	public static final String PW = "root";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	// 싱글톤 패턴 디자인
	private static Connection conn;
	
	// getter,setter의 변형이라고봐도 좋다
	public static Connection getConnection() throws Exception {
		// conn.close() 를 한다고 해서 null이 되지않는다.물어봐야함 .isClosed()함수
		if (conn == null || conn.isClosed()) {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PW);
		}
		return conn;
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try { 
			if (rs!=null)rs.close();
			if (pstmt!=null)pstmt.close();
			if (conn!=null)conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt, Connection conn) {
		try { 
			if (pstmt!=null)pstmt.close();
			if (conn!=null)conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
