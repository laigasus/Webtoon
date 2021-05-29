package com.webtoon.domain;

/*
create table webtoon(
no		INT(10)		not null primary key auto_increment,
day		tinyint(1)	default 0,
title	varchar(30),
url		varchar(255),
thumb	varchar(2000)
);
 */

public class WebtoonVO {

	private int no;
	private String day;
	private String title;
	private String url;
	private String thumb;

	public WebtoonVO() {
	}

	public WebtoonVO(String day, String title, String url, String thumb) {
		super();
		this.day = day;
		this.title = title;
		this.url = url;
		this.thumb = thumb;
	}

	public int getNo() {
		return no;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
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
