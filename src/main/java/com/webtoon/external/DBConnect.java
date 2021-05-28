package com.webtoon.external;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    Connection conn = null;

	public DBConnect() {

		try {
			// mariaDB 드라이버 설정
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 접속할 DB주소 설정
			String url = "jdbc:mysql://127.0.0.1:3306/webtoondb?characterEncoding=UTF-8&serverTimezone=UTC";

			// 접속할 아이디, 비밀번호 설정
			conn = DriverManager.getConnection(url, "webtoon", "abc1234");
			System.out.println("DB 연결 성공");

		} catch (ClassNotFoundException e){
			System.out.println("mySQL Driver를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println("Database 연결중 에러가 발생 했습니다.");
		}
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public boolean isClosed() throws SQLException {
		return conn.isClosed();
	}

	public void close() throws SQLException {
		conn.close();
	}
}