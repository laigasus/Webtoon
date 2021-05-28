package com.webtoon.domain;

/*
create table my_toon(
mt_title varchar(45) not null primary key,
mt_user varchar(45) not null,
mt_imgsrc mediumtext not null,
mt_url mediumtext not null
); 
*/


public class my_webtoon {
	private String imgSrc;
	private String webtoontitle;
	private String webtoonUrl;

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getImgSrc() {
		return this.imgSrc;
	}

	public void setWebtoonTitle(String webtoontitle) {
		this.webtoontitle = webtoontitle;
	}

	public String getWebtoonTitle() {
		return this.webtoontitle;
	}

	public void setWebtoonUrl(String webtoonUrl) {
		this.webtoonUrl = webtoonUrl;
	}

	public String getWebtoonUrl() {
		return this.webtoonUrl;
	}
}
