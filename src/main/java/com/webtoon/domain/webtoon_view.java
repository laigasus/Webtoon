package com.webtoon.domain;

/*
create table webtoon_view(
num int not null primary key auto_increment,
url varchar(255),
img varchar(2000)
);
 */

public class webtoon_view {

	private String url;
	private String img;

	public webtoon_view() {
	}

	public webtoon_view(String url, String img) {
		super();
		this.url = url;
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
