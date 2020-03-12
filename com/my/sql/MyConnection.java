package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

//	DB���������ڵ�
//	Connection con;
//	con = com.my.sql.MyConnection.getConnection();

	static Connection con = null;

//	public static Connection getConnection() throws Exception {
////
//		// 1)JDBC����̹� �ε�
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			throw new Exception("JDBC����̹� �ε����");
//		}
//		// 2)DB����
//		String ur1 = "jdbc:oracle:thin:@localhost:1521:xe"; // ����Ŭ�� JDBC
//		String user = "ora1"; // hr����
//		String password = "ora1"; // hr���
//		try {
//			con = DriverManager.getConnection(ur1, user, password);
//		} catch (SQLException e) {
//			throw new Exception(user + "���� ���ӽ���");
//		}
//		// 3)��ȯ
//		return con;
//
//	}

	/**
	 * ����ŬDB�� �����Ѵ� localhost ȣ��Ʈ, ora1/ora1������ �����Ѵ�.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		return getConnection("localhost", "ora1", "ora1");
	}

	/**
	 * ����ŬDB�� �����Ѵ�
	 * 
	 * @param host     ������DB IP
	 * @param user     �����Ұ�����
	 * @param password ������ ���� ��й�ȣ
	 * @return Connection��ü
	 * @throws Exception jdbc����̹������̰ų� �������ӽ��п����޽����� ���´�.
	 */
	public static Connection getConnection(String host, String user, String password) throws Exception {

		// 1)JDBC����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new Exception("JDBC����̹� �ε����");
		}
		// 2)DB����
		String ur1 = "jdbc:oracle:thin:@" + host + ":1521:xe";

		try {
			con = DriverManager.getConnection(ur1, user, password);
		} catch (SQLException e) {
			throw new Exception(user + "���� ���ӽ���");
		}
		// 3)��ȯ
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
