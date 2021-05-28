package com.webtoon.service;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import com.crawling.model.webtoonDAO;

public class naverDAO implements InaverDAO {
	DataSource ds;

	private naverDAO() {

		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static naverDAO dao = new naverDAO();

	public static naverDAO getInstance() {
		if (dao == null)
			dao = new naverDAO();
		return dao;
	}

	////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<naverVO> listBoard(int YoIll) {
		
		webtoonDAO.getInstance().webtoonCrawling();

		ArrayList<naverVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM webtoon where day=? ORDER BY no ASC";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, YoIll);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				naverVO article = new naverVO(rs.getInt("no"), rs.getInt("day"), rs.getString("title"),
						rs.getString("url"), rs.getString("thumb"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public ArrayList<naverVO> searchBoard(String searchParam) {
		
		webtoonDAO.getInstance().webtoonCrawling();

		ArrayList<naverVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM webtoon where title LIKE " + "'%" + searchParam + "%'" + " ORDER BY no ASC";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				naverVO article = new naverVO(rs.getInt("no"), rs.getInt("day"), rs.getString("title"),
						rs.getString("url"), rs.getString("thumb"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	public ArrayList<listVO> toonList(String URL, String id) {
		
		webtoonDAO.getInstance().webtoonListCrawling(URL, id);

		ArrayList<listVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM webtoon_list where id=? ORDER BY num ASC";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listVO article = new listVO(rs.getString("id"), rs.getString("thumb"), rs.getString("title"),
						rs.getString("url"), rs.getString("day"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	public ArrayList<viewVO> toonView(String URL) {
		
		webtoonDAO.getInstance().webtoonViewCrawling(URL);

		ArrayList<viewVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM webtoon_view where url=? ORDER BY num ASC";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, URL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				viewVO article = new viewVO(rs.getInt("num"), rs.getString("url"), rs.getString("img"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public ArrayList<String> toonInfo(String URL) {
		return webtoonDAO.getInstance().webtoonInfoCrawling(URL);
	}
}
