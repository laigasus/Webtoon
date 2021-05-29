package com.webtoon.service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.MyWebtoonVO;
import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonViewVO;
import com.webtoon.mapper.WebtoonMapper;

@Service("WebtoonService")
public class WebtoonServiceImpl implements WebtoonService {

	@Autowired
	private WebtoonMapper webtoonMapper;

	/////////////////////////////////////////////////////////

	@Override
	public void insertWebtoon(int day, String title, String url, String thumb) {
		webtoonMapper.insertWebtoon(day, title, url, thumb);
	}

	@Override
	public void insertWebtoonList(String id, String thumb, String title, String url, String day) {
		webtoonMapper.insertWebtoonList(id, thumb, title, url, day);
	}

	@Override
	public void insertWebtoonView(String url, String img) {
		webtoonMapper.insertWebtoonView(url, img);
	}

	@Override
	public int myWebtoonCheck(String webtoonTitle, String login) {
		MyWebtoonVO vo = webtoonMapper.myWebtoonCheck(webtoonTitle, login);
		int check = 0;
		if (vo.getWebtoonTitle().equals(webtoonTitle) && vo.getUserEmail().equals(login)) {
			check = 1;
		} else {
			check = 0;
		}
		return check;
	}

	@Override
	public void myWebtoonUpload(String imgSrc, String webtoonTitle, String webtoonUrl, String login) {
		webtoonMapper.myWebtoonUpload(imgSrc, webtoonTitle, webtoonUrl, login);
	}

	@Override
	public void myWebtoonDelete(String webtoonTitle, String login) {
		webtoonMapper.myWebtoonDelete(webtoonTitle, login);
	}

	@Override
	public int selectQuery(String title) {
		return webtoonMapper.selectQuery(title);
	}

	@Override
	public void deleteQuery(String tableName) {
		webtoonMapper.deleteQuery(tableName);
	}

	@Override
	public void webtoonCrawling() {
		deleteQuery(WebtoonVO.class.getSimpleName());

		Document doc = null;
		Elements elemList = new Elements();
		String URL = "https://comic.naver.com/webtoon/weekday.nhn";
		String[] dateArr = { "h4.mon", "h4.tue", "h4.wed", "h4.thu", "h4.fri", "h4.sat", "h4.sun" };

		try {
			Connection connection = Jsoup.connect(URL).userAgent(
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.header("Accept", "text/html").header("Accept-Encoding", "gzip,deflate")
					.header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
					.header("Connection", "keep-alive").ignoreContentType(true).timeout(30000);
			doc = connection.get();
			elemList = doc.select(".col_inner");

			if (elemList.size() > 0) {

				for (int i = 0; i < elemList.size(); i++) {
					Elements elems = elemList.get(i).children();

					if (elems.size() > 0) {

						for (int j = 0; j < dateArr.length; j++) {
							Elements elemTitle = new Elements();
							Elements elemUl = new Elements();
							elemTitle = elems.select(dateArr[j]);

							if (elemTitle != null && !elemTitle.isEmpty()) {

								elemUl = elems.select("ul > li");
								if (elemUl.size() > 0) {

									for (int k = 0; k < elemUl.size(); k++) {
										Elements elemLi = elemUl.get(k).children();

										if (selectQuery(elemLi.text()) < 1) {
											Elements elemHref = elemLi.select(".thumb > a");
											Elements elemImg = elemLi.select(".thumb > a > img");

											insertWebtoon(j, elemLi.attr("title"), elemHref.attr("href"),
													elemImg.attr("src"));
										}
									}
								}
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void webtoonListCrawling(String URL, String id) {
		deleteQuery(WebtoonListVO.class.getSimpleName());

		Document doc = null;
		Elements elemList = new Elements();

		try {
			Connection connection = Jsoup.connect(URL).userAgent(
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.header("Accept", "text/html").header("Accept-Encoding", "gzip,deflate")
					.header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
					.header("Connection", "keep-alive").ignoreContentType(true).timeout(30000);
			doc = connection.get();

			elemList = doc.select(".viewList");

			if (elemList.size() > 0) {

				for (int i = 0; i < elemList.size(); i++) {
					Elements elems = elemList.get(i).children();

					if (elems.size() > 0) {
						Elements elemTbody = new Elements();
						elemTbody = elems.select("tbody");

						if (elemTbody.size() > 0) {
							Elements elemTr = new Elements();
							elemTr = elems.select("tr");

							if (elemTr.size() > 0) {

								for (int k = 0; k < elemTr.size(); k++) {
									Elements elemTd = elemTr.get(k).children();

									if (selectQuery(elemTd.text()) < 1) {
										Elements elemHref = elemTd.select("a");
										Elements elemEpisodeThumb = elemTd.select("a > img");
										Elements elemEpisodeTitle = elemTd.select(".title");
										Elements elemEpisodeDate = elemTd.select(".num");

										if (elemEpisodeTitle.text().equals("")) {
											continue;
										} else {
											insertWebtoonList(id, elemEpisodeThumb.attr("src"), elemEpisodeTitle.text(),
													elemHref.attr("href"), elemEpisodeDate.text());
										}

									}
								}
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void webtoonViewCrawling(String URL) {
		deleteQuery(WebtoonViewVO.class.getSimpleName());

		Document doc = null;
		Elements elemList = new Elements();

		try {
			Connection connection = Jsoup.connect(URL).userAgent(
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.header("Accept", "text/html").header("Accept-Encoding", "gzip,deflate")
					.header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
					.header("Connection", "keep-alive").ignoreContentType(true).timeout(30000);
			doc = connection.get();
			elemList = doc.select(".view_area");

			if (elemList.size() > 0) {

				if (elemList.size() > 0) {

					for (int i = 0; i < elemList.size(); i++) {
						Elements elems = elemList.get(i).children();

						if (elems.size() > 0) {
							Elements wt_viewer = new Elements();
							wt_viewer = elems.select(".wt_viewer");

							if (wt_viewer.size() > 0) {

								for (int j = 0; j < wt_viewer.size(); j++) {
									Elements elemTd = wt_viewer.get(j).children();

									if (elemTd.size() > 0) {
										for (int k = 0; k < elemTd.size(); k++) {
											if (selectQuery(elemTd.text()) < 1) {

												Elements IMG = elemTd.select("#content_image_" + k);
												insertWebtoonView(URL, IMG.attr("src"));

											}
										}
									}
								}
							}
						}
					}
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> webtoonInfoCrawling(String URL) {
		ArrayList<String> infoArr = new ArrayList<String>(4);// 순차적으로 이미지, 제목, 작가, 장르

		Document doc = null;
		Elements elemList = new Elements();

		try {
			Connection connection = Jsoup.connect(URL).userAgent(
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.header("Accept", "text/html").header("Accept-Encoding", "gzip,deflate")
					.header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
					.header("Connection", "keep-alive").ignoreContentType(true).timeout(30000);
			doc = connection.get();

			elemList = doc.select(".comicinfo");

			if (elemList.size() > 0) {

				for (int i = 0; i < elemList.size(); i++) {
					Elements IMG = elemList.select(".thumb > a > img");
					Elements elems = elemList.get(i).children();

					if (elems.size() > 0) {
						Elements detail = new Elements();
						detail = elems.select(".detail");

						if (detail.size() > 0) {

							Elements title = detail.select("h2");
							Elements author = detail.select("h2 > span");
							Elements genre = detail.select("p > .genre");

							infoArr.add(IMG.attr("src"));
							infoArr.add(title.text());
							infoArr.add(genre.text());
							infoArr.add(author.text());

						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return infoArr;
	}

	@Override
	public ArrayList<MyWebtoonVO> getMyWebtoonList(String email) {
		return webtoonMapper.getMyWebtoonList(email);
	}
}
