package com.webtoon.domain;

/*
create table webtoon_list(
id		varchar(10),
num int not null primary key auto_increment,
thumb	varchar(2000) not null,
title	varchar(30) not null,
url		varchar(255),
day		varchar(10)
);
 */

public class webtoon_list {

	private String id;
	private String thumb;
	private String title;
	private String url;
	private String day;

	public webtoon_list() {
	}

	public webtoon_list(String id, String thumb, String title, String url, String day) {
		super();
		this.id = id;
		this.thumb = thumb;
		this.title = title;
		this.url = url;
		this.day = day;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
