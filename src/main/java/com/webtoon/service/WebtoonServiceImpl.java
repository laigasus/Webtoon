package com.webtoon.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.MyWebtoonVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonViewVO;
import com.webtoon.mapper.WebtoonMapper;

@Service("WebtoonService")
public class WebtoonServiceImpl implements WebtoonService {

	@Autowired
	private WebtoonMapper webtoonMapper;

	DataSource ds;

	public WebtoonServiceImpl() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/////////////////////////////////////////////////////////

	@Override
	public void insertQuery(int day, String title, String url, String thumb) {

		String sql = "INSERT INTO webtoon (no, day, title, url, thumb) VALUES (0, ?, ?, ?, ?) ";
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int index = 1;
			pstmt.setInt(index++, day);
			pstmt.setString(index++, title);
			pstmt.setString(index++, url);
			pstmt.setString(index++, thumb);

			int count = pstmt.executeUpdate();

			if (count == 0) {
				System.out.println("데이터값에 이상이 있습니다.");
			} else {
				// System.out.println("데이터 Insert 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insertQuery(String id, String thumb, String title, String url, String day) {

		String sql = "INSERT INTO WebtoonListVO (id, thumb, title, url, day) VALUES (?, ?, ?, ?, ?) ";
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int index = 1;
			pstmt.setString(index++, id);
			pstmt.setString(index++, thumb);
			pstmt.setString(index++, title);
			pstmt.setString(index++, url);
			pstmt.setString(index++, day);

			int count = pstmt.executeUpdate();

			if (count == 0) {
				System.out.println("데이터값에 이상이 있습니다.");
			} else {
				// System.out.println("데이터 Insert 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insertQuery(String url, String img) {

		String sql = "INSERT INTO WebtoonViewVO (url, num, img) VALUES (?, 0, ?) ";
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int index = 1;
			pstmt.setString(index++, url);
			pstmt.setString(index++, img);

			int count = pstmt.executeUpdate();

			if (count == 0) {
				System.out.println("데이터값에 이상이 있습니다.");
			} else {
				// System.out.println("데이터 Insert 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int myWebtoonCheck(String webtoonTitle, String login) {

		int check = 0;

		String sql = "select * from my_toon WHERE mt_title=? AND mt_user=?";

		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, webtoonTitle);
			pstmt.setString(2, login);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String mt_title = rs.getString("mt_title");
				String mt_user = rs.getString("mt_user");
				if (mt_title.equals(webtoonTitle) && mt_user.equals(login)) {
					check = 1;
				}
			} else
				check = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public void myWebtoonUpload(String imgSrc, String webtoonTitle, String webtoonUrl, String login) {

		String sql = "insert into my_toon values(?,?,?,?)";
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, webtoonTitle);
			pstmt.setString(2, login);
			pstmt.setString(3, imgSrc);
			pstmt.setString(4, webtoonUrl);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void myWebtoonDelete(String webtoonTitle, String login) {

		String sql = "delete from my_toon where mt_title=? AND mt_user=?";
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, webtoonTitle);
			pstmt.setString(2, login);
			pstmt.executeUpdate();
			System.out.println("myWebtoonDelete실행");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int selectQuery(String title) {

		int result = 0;
		String sql = "SELECT count(*) as cnt FROM webtoon where title = ?";
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = null;

			int pstmtIndex = 1;
			pstmt.setString(pstmtIndex++, title);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
				break;
			}
			if (rs.equals(null)) {
				System.out.println("데이터값에 이상이 있습니다.");
			} else {
				// System.out.println("데이터 Insert 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void deleteQuery(String tableName) {

		String sql = "TRUNCATE " + tableName;
		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			boolean count = pstmt.execute();

			if (count) {
				System.out.println("데이터값에 이상이 있습니다.");
			} else {
				// System.out.println("데이터 Insert 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void webtoonCrawling() {

		deleteQuery(WebtoonVO.class.getSimpleName());

		Document doc = null;
		Elements elemList = new Elements();
		String URL = "https://comic.naver.com/webtoon/weekday.nhn";
		String[] dateArr = { "h4.mon", "h4.tue", "h4.wed", "h4.thu", "h4.fri", "h4.sat", "h4.sun" };

		try {
			org.jsoup.Connection connection = Jsoup.connect(URL).userAgent(
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

											insertQuery(j, elemLi.attr("title"), elemHref.attr("href"),
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
											insertQuery(id, elemEpisodeThumb.attr("src"), elemEpisodeTitle.text(),
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
												insertQuery(URL, IMG.attr("src"));

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

		ArrayList<String> infoArr = new ArrayList<>(4);// 순차적으로 이미지, 제목, 작가, 장르

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

		ArrayList<MyWebtoonVO> datas = new ArrayList<MyWebtoonVO>();
		String sql = "select * from my_toon where mt_user ='".concat(email) + "';";

		try (java.sql.Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MyWebtoonVO mywebtoon = new MyWebtoonVO();
				mywebtoon.setImgSrc(rs.getString("mt_imgsrc"));
				mywebtoon.setWebtoonTitle(rs.getString("mt_title"));
				mywebtoon.setWebtoonUrl(rs.getString("mt_url"));
				datas.add(mywebtoon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;
	}
}
