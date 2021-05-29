package com.webtoon.domain;

/*
create table my_toon(
mt_title varchar(45) not null primary key,
mt_user varchar(45) not null,
mt_imgsrc mediumtext not null,
mt_url mediumtext not null
); 
*/

public class MyWebtoonVO {
	private String imgSrc;
	private String webtoonTitle;
	private String webtoonUrl;
	private String userEmail;

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getWebtoonTitle() {
		return webtoonTitle;
	}

	public void setWebtoonTitle(String webtoonTitle) {
		this.webtoonTitle = webtoonTitle;
	}

	public String getWebtoonUrl() {
		return webtoonUrl;
	}

	public void setWebtoonUrl(String webtoonUrl) {
		this.webtoonUrl = webtoonUrl;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
