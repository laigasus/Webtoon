package com.webtoon.domain;

/*CREATE TABLE `webtoon_view` (
		  `num` int NOT NULL AUTO_INCREMENT,
		  `url` varchar(255) DEFAULT NULL,
		  `img` varchar(2000) DEFAULT NULL,
		  PRIMARY KEY (`num`)
		);
*/

public class WebtoonViewVO {
	private int num;
	private String url;
	private String img;

	public WebtoonViewVO() {

	}

	public WebtoonViewVO(int num, String url, String img) {
		super();
		this.num = num;
		this.url = url;
		this.img = img;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	@Override
	public String toString() {
		return "WebtoonViewVO [num=" + num + ", url=" + url + ", img=" + img + "]";
	}

}
