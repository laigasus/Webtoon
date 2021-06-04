package com.webtoon.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webtoon.domain.UserVO;

public interface UserMapper {

	// 중복 ID 여부를 검증하는 메서드
	@Select("SELECT email FROM toon_user WHERE email=#{email}")
	public UserVO CheckDuplicate(@Param("email") String email);

	// 회원 가입을 처리하는 메서드
	@Select("INSERT INTO toon_user VALUES (#{email}, #{pw}, #{nick})")
	public void insertUser(UserVO vo);

	// 로그인 유효성을 검증하는 메서드
	@Select("SELECT * FROM toon_user WHERE email=#{email}")
	public UserVO userCheck(@Param("email") String email, @Param("password") String password);

	// 이메일을 받아 존재하는 이메일인지 확인하는 메서드(유효: 1, 중복: -1)
	@Select("SELECT * FROM toon_user WHERE email=#{email}")
	public UserVO userCheckEmail(@Param("email") String email);

	// 특정 회원의 모든 정보를 얻어오는 메서드.
	@Select("SELECT * FROM toon_user WHERE email=#{email}")
	public UserVO getUserInfo(@Param("email") String email);

	// 비밀번호를 변경하는 메서드
	@Update("UPDATE toon_user SET pw=#{password} WHERE email=#[email}")
	public void changePassword(@Param("email") String email, @Param("password") String pw);

	// 회원 탈퇴를 처리할 메서드
	@Delete("DELETE FROM toon_user WHERE email=#{email}")
	public void deleteUser(@Param("email") String email);

	// 회원 정보 수정을 처리할 메서드
	@Update("UPDATE toon_user SET nick=#{nick} WHERE email=#{email}")
	public void updateUser(UserVO vo);

	// 관리자로써 회원의 목록를 출력하는 메서드
	@Select("SELECT * FROM toon_user ORDER BY email DESC")
	public ArrayList<UserVO> listUser();
}
