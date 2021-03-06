package com.webtoon.domain;

import java.sql.Date;

/*  담당자 김민수 
CREATE TABLE `board_comment` (
  `bd_num` int NOT NULL,
  `cm_id` int NOT NULL AUTO_INCREMENT,
  `cm_writer` varchar(45) NOT NULL,
  `cm_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cm_content` varchar(1000) DEFAULT NULL,
  `cm_like` int DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`cm_id`),
  KEY `fk_board_comment_toon_board1_idx` (`bd_num`),
  CONSTRAINT `fk_board_comment_toon_board` FOREIGN KEY (`bd_num`) REFERENCES `toon_board` (`bd_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/

public class CommentVO {

	private int bd_num;
	private int cm_id;
	private String cm_writer;
	private Date cm_date;
	private String cm_content;
	private int cm_like;
	private String email;

	public CommentVO() {
	}

	public CommentVO(int bd_num, int cm_id, String cm_writer, Date cm_date, String cm_content, int cm_like, String email) {
		super();
		this.bd_num = bd_num;
		this.cm_id = cm_id;
		this.cm_writer = cm_writer;
		this.cm_date = cm_date;
		this.cm_content = cm_content;
		this.cm_like = cm_like;
		this.email = email;
	}

	public int getBd_num() {
		return bd_num;
	}

	public void setBd_num(int bd_num) {
		this.bd_num = bd_num;
	}

	public int getCm_id() {
		return cm_id;
	}

	public void setCm_id(int cm_id) {
		this.cm_id = cm_id;
	}

	public String getCm_writer() {
		return cm_writer;
	}

	public void setCm_writer(String cm_writer) {
		this.cm_writer = cm_writer;
	}

	public Date getCm_date() {
		return cm_date;
	}

	public void setCm_date(Date cm_date) {
		this.cm_date = cm_date;
	}

	public String getCm_content() {
		return cm_content;
	}

	public void setCm_content(String cm_content) {
		this.cm_content = cm_content;
	}

	public int getCm_like() {
		return cm_like;
	}

	public void setCm_like(int cm_like) {
		this.cm_like = cm_like;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CommentVO [bd_num=" + bd_num + ", cm_id=" + cm_id + ", cm_writer=" + cm_writer + ", cm_date=" + cm_date
				+ ", cm_content=" + cm_content + ", cm_like=" + cm_like + ", email=" + email + "]";
	}

	
}
