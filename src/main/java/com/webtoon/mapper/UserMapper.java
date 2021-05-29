package com.webtoon.mapper;

import java.net.PasswordAuthentication;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webtoon.domain.UserVO;

public interface UserMapper {

	// String email
	// 중복 ID 여부를 검증하는 메서드
	@Select("SELECT email FROM toon_user WHERE email=#{email}")
	public UserVO CheckDuplicate(String email);

	// UserVO vo
	// 회원 가입을 처리하는 메서드
	@Select("INSERT INTO toon_user VALUES (#{email}, #{password}, #{nickname})")
	public void insertUser(UserVO vo);

	// String email, String pw
	// 로그인 유효성을 검증하는 메서드
	@Select("SELECT * FROM toon_user WHERE email=#{email}")
	public UserVO userCheck(String email, String pw);

	// String email
	// 이메일을 받아 존재하는 이메일인지 확인하는 메서드(유효: 1, 중복: -1)
	@Select("SELECT * FROM toon_user WHERE email=#{email}")
	public UserVO userCheckEmail(String email);

	// String email
	// 특정 회원의 모든 정보를 얻어오는 메서드.
	@Select("SELECT * FROM toon_user WHERE email=#{email}")
	public UserVO getUserInfo(String email);

	// String email, String pw
	// 비밀번호를 변경하는 메서드
	@Update("UPDATE toon_user SET pw=#{password} WHERE email=#[email}")
	public void changePassword(String email, String pw);

	// String email
	// 회원 탈퇴를 처리할 메서드
	@Delete("DELETE FROM toon_user WHERE email=#{email}")
	public void deleteUser(String email);

	// UserVO vo
	// 회원 정보 수정을 처리할 메서드
	@Update("UPDATE toon_user " + "SET nick=#{nickname} " + "WHERE email=#{email}")
	public void updateUser(UserVO vo);

	// 원래 매개변수가 아무것도 없었음. 채움
	// 관리자로써 회원의 목록를 출력하는 메서드
	@Select("SELECT * FROM toon_user ORDER BY email DESC")
	public List<UserVO> listUser();

//	// 비밀번호 변경시 이메일을통해 변경된 비밀번호를 전송하는 메서드
//	// SQL문이 없어!!!!!!!!!!!!!!!!!!!!!
//	public PasswordAuthentication getPasswordAuthentication();
}
