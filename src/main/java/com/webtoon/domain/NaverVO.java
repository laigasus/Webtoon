package com.webtoon.domain;

/*CREATE TABLE `webtoon` (
		  `no` int NOT NULL AUTO_INCREMENT,
		  `day` tinyint(1) DEFAULT '0',
		  `title` varchar(30) DEFAULT NULL,
		  `url` varchar(255) DEFAULT NULL,
		  `thumb` varchar(2000) DEFAULT NULL,
		  PRIMARY KEY (`no`)
		);
*/

public class NaverVO {
	private int no;
	private int day;
	private String title;
	private String url;
	private String thumb;

	public NaverVO() {
	}

	public NaverVO(int no, int day, String title, String url, String thumb) {
		super();
		this.no = no;
		this.day = day;
		this.title = title;
		this.url = url;
		this.thumb = thumb;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
}
