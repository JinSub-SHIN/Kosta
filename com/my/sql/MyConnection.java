package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

//	DB연결사용자코드
//	Connection con;
//	con = com.my.sql.MyConnection.getConnection();

	static Connection con = null;

//	public static Connection getConnection() throws Exception {
////
//		// 1)JDBC드라이버 로드
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			throw new Exception("JDBC드라이버 로드실패");
//		}
//		// 2)DB연결
//		String ur1 = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클용 JDBC
//		String user = "ora1"; // hr계정
//		String password = "ora1"; // hr비번
//		try {
//			con = DriverManager.getConnection(ur1, user, password);
//		} catch (SQLException e) {
//			throw new Exception(user + "계정 접속실패");
//		}
//		// 3)반환
//		return con;
//
//	}

	/**
	 * 오라클DB에 접속한다 localhost 호스트, ora1/ora1계정에 접속한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		return getConnection("localhost", "ora1", "ora1");
	}

	/**
	 * 오라클DB에 접속한다
	 * 
	 * @param host     접속할DB IP
	 * @param user     접속할계정명
	 * @param password 접속할 계정 비밀번호
	 * @return Connection객체
	 * @throws Exception jdbc드라이버오류이거나 계정접속실패오류메시지를 갖는다.
	 */
	public static Connection getConnection(String host, String user, String password) throws Exception {

		// 1)JDBC드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new Exception("JDBC드라이버 로드실패");
		}
		// 2)DB연결
		String ur1 = "jdbc:oracle:thin:@" + host + ":1521:xe";

		try {
			con = DriverManager.getConnection(ur1, user, password);
		} catch (SQLException e) {
			throw new Exception(user + "계정 접속실패");
		}
		// 3)반환
		return con;
	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
