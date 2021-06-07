package com.webtoon.domain;

/*   담당자 옥재욱
 * CREATE TABLE `webtoon_list` (
		  `id` varchar(10) DEFAULT NULL,
		  `num` int NOT NULL AUTO_INCREMENT,
		  `thumb` varchar(2000) NOT NULL,
		  `title` varchar(30) NOT NULL,
		  `url` varchar(255) DEFAULT NULL,
		  `day` varchar(10) DEFAULT NULL,
		  PRIMARY KEY (`num`)
		);
*/

public class WebtoonListVO {
	private String id;
	private String thumb;
	private String title;
	private String url;
	private String day;

	public WebtoonListVO() {

	}

	public WebtoonListVO(String id, String thumb, String title, String url, String day) {
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

	@Override
	public String toString() {
		return "WebtoonListVO [id=" + id + ", thumb=" + thumb + ", title=" + title + ", url=" + url + ", day=" + day
				+ "]";
	}

}
