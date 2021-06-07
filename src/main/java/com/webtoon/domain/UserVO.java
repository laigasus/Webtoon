package com.webtoon.domain;

/* 담당자 김민수 
CREATE TABLE `toon_user` (
  `email` varchar(45) NOT NULL,
  `nick` varchar(45) DEFAULT NULL,
  `pw` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
);
 */

public class UserVO {

	private String email;
	private String nick;
	private String pw;

	public UserVO() {
	}

	public UserVO(String email, String nick, String pw) {
		super();
		this.email = email;
		this.nick = nick;
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "UserVO [email=" + email + ", nick=" + nick + ", pw=" + pw + "]";
	}

	
}
