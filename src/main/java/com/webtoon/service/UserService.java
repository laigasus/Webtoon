package com.webtoon.service;

import java.net.PasswordAuthentication;
import java.util.List;

import com.webtoon.domain.UserVO;

public interface UserService {
	// 중복 ID 여부를 검증하는 메서드
	boolean CheckDuplicate(String email);

	// 회원 가입을 처리하는 메서드
	void insertUser(UserVO vo);

	// 로그인 유효성을 검증하는 메서드
	int userCheck(String email, String pw);

	// 이메일을 받아 존재하는 이메일인지 확인하는 메서드(유효: 1, 중복: -1)
	int userCheckEmail(String email);

	// 특정 회원의 모든 정보를 얻어오는 메서드.
	UserVO getUserInfo(String email);

	// 비밀번호를 변경하는 메서드
	void changePassword(String email, String pw);

	// 비밀번호 변경시 이메일을통해 변경된 비밀번호를 전송하는 메서드
	PasswordAuthentication getPasswordAuthentication();

	// 회원 탈퇴를 처리할 메서드
	void deleteUser(String email);

	// 회원 정보 수정을 처리할 메서드
	void updateUser(UserVO vo);

	// 관리자로써 회원의 목록를 출력하는 메서드
	List<UserVO> listUser();
}
