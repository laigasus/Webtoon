package com.webtoon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.CommentVO;
import com.webtoon.mapper.CommentMapper;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	DataSource ds;

	private CommentServiceImpl() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////////

	@Override
	public void regist(int bd_num, String cm_writer, String cm_content, String email) {

		String sql = "INSERT INTO board_comment " + "(bd_num, cm_writer, cm_content, cm_like, email) "
				+ "VALUES (?, ?, ?, ?,?)";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bd_num);
			pstmt.setString(2, cm_writer);
			pstmt.setString(3, cm_content);
			pstmt.setInt(4, 0);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommentVO> listComment(int bd_num) {

		List<CommentVO> articles = new ArrayList<>();
		String sql = "SELECT * FROM board_comment WHERE bd_num=?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bd_num);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				CommentVO article = new CommentVO(rs.getInt("bd_num"), rs.getInt("cm_id"), rs.getString("cm_writer"),
						rs.getDate("cm_date"), rs.getString("cm_content"), rs.getInt("cm_like"),
						rs.getNString("email"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public void updateComment(String cm_writer, String cm_content, int bd_num, int cm_id) {

		String sql = "UPDATE board_comment SET " + "cm_content=? " + "WHERE bd_num=? AND cm_id=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cm_content);
			pstmt.setInt(2, bd_num);
			pstmt.setInt(3, cm_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComment(int bd_num, int cm_id) {

		String sql = "DELETE FROM board_comment WHERE bd_num=? AND cm_id=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bd_num);
			pstmt.setInt(2, cm_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CommentVO bestComment(int bd_num) {

		CommentVO best = null;
		String sql = "SELECT * FROM board_comment WHERE bd_num=? ORDER BY cm_like desc LIMIT 1";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bd_num);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				best = new CommentVO(rs.getInt("bd_num"), rs.getInt("cm_id"), rs.getString("cm_writer"),
						rs.getDate("cm_date"), rs.getString("cm_content"), rs.getInt("cm_like"),
						rs.getNString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return best;
	}

	@Override
	public void likeComment(int bd_num, int cm_id) {

		String sql = "UPDATE board_comment SET cm_like=cm_like+1 WHERE bd_num=? AND cm_id=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bd_num);
			pstmt.setInt(2, cm_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
