package com.webtoon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.mail.PasswordAuthentication;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.webtoon.domain.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService {

	DataSource ds;

	private UserServiceImpl() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/////////////////////////////////////////////////////////

	@Override
	public boolean CheckDuplicate(String email) {

		boolean result = false;
		String sql = "SELECT email FROM toon_user WHERE email=?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				result = true;
			else
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insertUser(UserVO vo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO toon_user VALUES (?,?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getNick());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int userCheck(String email, String pw) {

		int check = 0;

		String sql = "SELECT * FROM toon_user WHERE email=?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPw = rs.getString("pw");
				if (dbPw.equals(pw))
					check = 1; // 로그인성공
				else
					check = 0; // 없는 비밀번호
			} else
				check = -1; // 없는 이메일

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int userCheckEmail(String email) {

		int check = 0;

		String sql = "SELECT * FROM toon_user WHERE email=?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) { // 이메일이 없으면 else
				String dbEmail = rs.getString("email");
				if (dbEmail.equals(email))
					check = 1; // 로그인성공
			} else
				check = -1; // 없는 이메일

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public UserVO getUserInfo(String email) {

		UserVO vo = null;
		String sql = "SELECT * FROM toon_user WHERE email=?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new UserVO(rs.getString("email"), rs.getString("nick"), rs.getString("pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void changePassword(String email, String pw) {

		String sql = "UPDATE toon_user SET pw=? " + "WHERE email=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pw);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String email) {

		String sql = "DELETE FROM toon_user WHERE email=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(UserVO vo) {

		String sql = "UPDATE toon_user " + "SET nick=? " + "WHERE email=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getNick());
			pstmt.setString(2, vo.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserVO> listUser() {

		List<UserVO> users = new ArrayList<>();
		String sql = "SELECT * FROM toon_user ORDER BY email DESC";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				UserVO user = new UserVO(rs.getString("email"), rs.getString("nick"), rs.getString("pw"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("laigasus98@gmail.com", "okjaeook98");
	}

}
