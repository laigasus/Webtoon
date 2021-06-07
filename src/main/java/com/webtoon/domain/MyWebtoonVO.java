package com.webtoon.domain;

/*	담당자 옥재욱
CREATE TABLE `my_toon` (
  `mt_title` varchar(45) NOT NULL,
  `mt_user` varchar(45) NOT NULL,
  `mt_imgsrc` mediumtext,
  `mt_url` mediumtext,
  PRIMARY KEY (`mt_title`,`mt_user`),
  KEY `fk_my_toon_toon_user_idx` (`mt_user`),
  CONSTRAINT `fk_my_toon_toon_user` FOREIGN KEY (`mt_user`) REFERENCES `toon_user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/

public class MyWebtoonVO {

	private String mt_title;
	private String mt_user;
	private String mt_imgsrc;
	private String mt_url;

	public String getMt_title() {
		return mt_title;
	}

	public void setMt_title(String mt_title) {
		this.mt_title = mt_title;
	}

	public String getMt_user() {
		return mt_user;
	}

	public void setMt_user(String mt_user) {
		this.mt_user = mt_user;
	}

	public String getMt_imgsrc() {
		return mt_imgsrc;
	}

	public void setMt_imgsrc(String mt_imgsrc) {
		this.mt_imgsrc = mt_imgsrc;
	}

	public String getMt_url() {
		return mt_url;
	}

	public void setMt_url(String mt_url) {
		this.mt_url = mt_url;
	}

	@Override
	public String toString() {
		return "MyWebtoonVO [mt_title=" + mt_title + ", mt_user=" + mt_user + ", mt_imgsrc=" + mt_imgsrc + ", mt_url="
				+ mt_url + "]";
	}

}
