package com.webtoon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO implements IBoardDAO {

	DataSource ds;

	private BoardDAO() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static BoardDAO dao = new BoardDAO();

	public static BoardDAO getInstance() {
		if (dao == null)
			dao = new BoardDAO();
		return dao;
	}

	////////////////////////////////////////////////////////////////

	@Override
	public void regist(String email, String writer, String title, String content, String absoluteImgPath) {

		Timestamp time = new Timestamp(System.currentTimeMillis());
		String sql = "insert into toon_board values(null,?,?,?,?,?,?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setTimestamp(3, time);
			pstmt.setInt(4, 1);
			pstmt.setString(5, content);
			pstmt.setString(6, absoluteImgPath);
			pstmt.setString(7, email);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//startRow 부터 pageSize 갯수만큼 출력
	@Override
	public List<BoardVO> listBoard(int startRow,int pageSize) {
		ResultSet rs;
		List<BoardVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM toon_board WHERE bd_email !='root@naver.com' ORDER BY bd_num DESC LIMIT ?,?;";  
		//LIMIT가 bd_num 값으로 가져오는게 아니라 정렬해서 값순서대로 가져옴 이거몰라서 4시간씀

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO article = new BoardVO(rs.getInt("bd_num"), rs.getString("bd_writer"), rs.getString("bd_title"),
						rs.getString("bd_content"), rs.getString("bd_email"), rs.getDate("bd_date"),
						rs.getInt("bd_view"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<BoardVO> myListBoard(String email) {

		List<BoardVO> datas = new ArrayList<>();
		String sql = "select * from toon_board where bd_email ='".concat(email) + "' order by bd_num DESC;";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO post = new BoardVO(rs.getInt("bd_num"), rs.getString("bd_writer"), rs.getString("bd_title"),
						rs.getString("bd_content"), rs.getString("bd_email"), rs.getDate("bd_date"),
						rs.getInt("bd_view"));
				datas.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}

	@Override
	public BoardVO contentBoard(int bId) {

		BoardVO vo = null;
		String sql = "SELECT * FROM toon_board WHERE bd_num=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVO(rs.getInt("bd_num"), rs.getString("bd_writer"), rs.getString("bd_title"),
						rs.getString("bd_content"), rs.getString("bd_email"), rs.getDate("bd_date"),
						rs.getInt("bd_view"));
			}
		} catch (Exception e) {

		}
		return vo;
	}

	@Override
	public void updateBoard(String bd_title, String bd_content, String absoluteImgPath, int bId) {

		String sql = "UPDATE toon_board SET " + "bd_title=?, bd_content=?,bd_img=? " + "WHERE bd_num=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bd_title);
			pstmt.setString(2, bd_content);
			pstmt.setString(3, absoluteImgPath);
			pstmt.setInt(4, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBoard(int bId) {

		String sql = "DELETE FROM toon_board WHERE bd_num=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
			System.out.println("deleteBoard실행");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<BoardVO> searchBoard(String search, String category) {

		List<BoardVO> articles = new ArrayList<>();
		String sql = null;
		switch (category) {
		case "제목":
			sql = "SELECT * FROM toon_board WHERE bd_title LIKE ? AND bd_email !='root@naver.com'";
			break;
		case "작성자":
			sql = "SELECT * FROM toon_board WHERE bd_writer LIKE ? AND bd_email !='root@naver.com'";
			break;
		default:
			sql = "SELECT * FROM toon_board WHERE bd_email !='root@naver.com'";
		}
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + search + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO article = new BoardVO(rs.getInt("bd_num"), rs.getString("bd_writer"), rs.getString("bd_title"),
						rs.getString("bd_content"), rs.getString("bd_email"), rs.getDate("bd_date"),
						rs.getInt("bd_view"));
				articles.add(article);
			}
		} catch (Exception e) {

		}
		return articles;
	}

	@Override
	public void viewIncrease(int bId, int bView) {

		String sql = "UPDATE toon_board SET " + "bd_view=? " + "WHERE bd_num=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bView + 1);
			pstmt.setInt(2, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BoardVO> AdminListBoard() {

		List<BoardVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM toon_board WHERE bd_email='root@naver.com' ORDER BY bd_num DESC";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO article = new BoardVO(rs.getInt("bd_num"), rs.getString("bd_writer"), rs.getString("bd_title"),
						rs.getString("bd_content"), rs.getString("bd_email"), rs.getDate("bd_date"),
						rs.getInt("bd_view"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public int getCountBoard() {
		int count = 0;
		String sql = "select count(*) from toon_board"; //toon_board에 레코드수 select
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count; // 총 레코드 수 리턴
	}
}
