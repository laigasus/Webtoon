package com.webtoon.domain;

import java.sql.Date;

/*    담당자 김민수 
CREATE TABLE `toon_board` (
  `bd_num` int NOT NULL AUTO_INCREMENT,
  `bd_title` varchar(45) NOT NULL,
  `bd_writer` varchar(45) NOT NULL,
  `bd_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bd_view` int DEFAULT NULL,
  `bd_content` mediumtext,
  `bd_img` mediumtext,
  `bd_email` varchar(45) NOT NULL,
  PRIMARY KEY (`bd_num`),
  KEY `fk_toon_board_toon_user1_idx` (`bd_email`),
  CONSTRAINT `fk_toon_board_toon_user` FOREIGN KEY (`bd_email`) REFERENCES `toon_user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/

public class BoardVO {

	private int bd_num;
	private String bd_writer;
	private String bd_title;
	private String bd_content;
	private Date bd_date;
	private int bd_view;
	private String bd_email;

	public BoardVO() {
	}

	public BoardVO(int bd_num, String bd_writer, String bd_title, String bd_content, String bd_email, Date bd_date,
			int bd_view) {
		super();
		this.bd_num = bd_num;
		this.bd_writer = bd_writer;
		this.bd_title = bd_title;
		this.bd_content = bd_content;
		this.bd_email = bd_email;
		this.bd_date = bd_date;
		this.bd_view = bd_view;
	}

	public int getBd_num() {
		return bd_num;
	}

	public void setBd_num(int bd_num) {
		this.bd_num = bd_num;
	}

	public String getBd_writer() {
		return bd_writer;
	}

	public void setBd_writer(String bd_writer) {
		this.bd_writer = bd_writer;
	}

	public String getBd_title() {
		return bd_title;
	}

	public void setBd_title(String bd_title) {
		this.bd_title = bd_title;
	}

	public String getBd_content() {
		return bd_content;
	}

	public void setBd_content(String bd_content) {
		this.bd_content = bd_content;
	}

	public Date getBd_date() {
		return bd_date;
	}

	public void setBd_date(Date bd_date) {
		this.bd_date = bd_date;
	}

	public int getBd_view() {
		return bd_view;
	}

	public void setBd_view(int bd_view) {
		this.bd_view = bd_view;
	}

	public String getBd_email() {
		return bd_email;
	}

	public void setBd_email(String bd_email) {
		this.bd_email = bd_email;
	}

	@Override
	public String toString() {
		return "BoardVO [bd_num=" + bd_num + ", bd_writer=" + bd_writer + ", bd_title=" + bd_title + ", bd_content="
				+ bd_content + ", bd_date=" + bd_date + ", bd_view=" + bd_view + ", bd_email=" + bd_email + "]";
	}

}
