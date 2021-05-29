package com.webtoon.service;

import java.util.List;

import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.UserVO;
import com.webtoon.mapper.UserMapper;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/////////////////////////////////////////////////////////

	@Override
	public boolean CheckDuplicate(String email) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		userMapper.insertUser(vo);
	}

	@Override
	public int userCheck(String email, String pw) {
		// TODO Auto-generated method stub
		UserVO vo = userMapper.userCheck(email, pw);
		int check = 0;
		if (vo.getEmail().equals(email)) {
			if (vo.getPw().equals(pw)) {
				check = 1; // 로그인 성공
			} else {
				check = 0; // 없는 비밀번호
			}
		} else {
			check = -1;
		}
		return check;
	}

	@Override
	public int userCheckEmail(String email) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return userMapper.getUserInfo(email);
	}

	@Override
	public void changePassword(String email, String pw) {
		// TODO Auto-generated method stub
		userMapper.changePassword(email, pw);
	}

	@Override
	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(email);
	}

	@Override
	public void updateUser(UserVO vo) {
		// TODO Auto-generated method stub
		userMapper.updateUser(vo);
	}

	@Override
	public List<UserVO> listUser() {
		// TODO Auto-generated method stub
		return userMapper.listUser();
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication("laigasus98@gmail.com", "okjaeook98");
	}

}
