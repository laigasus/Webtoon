package com.webtoon.service;

import java.util.ArrayList;

import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.UserVO;
import com.webtoon.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/////////////////////////////////////////////////////////

	@Override
	public boolean CheckDuplicate(String email) {
		boolean result = false;
		if (userMapper.CheckDuplicate(email) != null) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	@Override
	public void insertUser(UserVO vo) {
		userMapper.insertUser(vo);
	}

	@Override
	public int userCheck(String email, String password) {
		int check = -1;
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		UserVO vo = userMapper.userCheck(email, password);
		if (vo == null) {
			check = -1;
		} else {
			if (vo.getEmail().equals(email)) {
				if (vo.getPw().equals(password)) {
					check = 1; // 로그인 성공
				} else {
					check = 0; // 없는 비밀번호
				}
			}
		}

		return check;
	}

	@Override
	public int userCheckEmail(String email) {
		UserVO vo = userMapper.userCheckEmail(email);
		int check = 0;
		if (vo.getEmail() != null) {
			String dbEmail = vo.getEmail();
			if (dbEmail.equals(email)) {
				check = 1; // 로그인 성공
			}
		} else {
			check = -1;
		}

		return check;
	}

	@Override
	public UserVO getUserInfo(String email) {
		return userMapper.getUserInfo(email);
	}

	@Override
	public void changePassword(String email, String password) {
		userMapper.changePassword(email, password);
	}

	@Override
	public void deleteUser(String email) {
		userMapper.deleteUser(email);
	}

	@Override
	public void updateUser(UserVO vo) {
		userMapper.updateUser(vo);
	}

	@Override
	public ArrayList<UserVO> listUser() {
		return userMapper.listUser();
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("laigasus98@gmail.com", "okjaeook98");
	}

}
