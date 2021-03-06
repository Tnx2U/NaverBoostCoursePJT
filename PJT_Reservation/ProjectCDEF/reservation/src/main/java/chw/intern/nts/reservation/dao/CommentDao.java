package chw.intern.nts.reservation.dao;

import static chw.intern.nts.reservation.dao.sql.CommentDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.CommentImage;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectAllByDisplayInfoId(Integer displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		
		return jdbcTemplate.query(SELECT_ALL_BY_DISPLAY_INFO_ID, param, commentRowMapper);
	}

	public List<CommentImage> selectAllByCommentId(Integer commentId) {
		Map<String, Integer> param = Collections.singletonMap("commentId", commentId);
		
		return jdbcTemplate.query(SELECT_COMMENT_IMAGES_ALL_BY_COMMENT_ID, param, commentImageRowMapper);
	}

	public Comment selectById(Integer commentId) {
		Map<String, Integer> params = Collections.singletonMap("commentId", commentId);
		
		return jdbcTemplate.queryForObject(SELECT_BY_ID, params, commentRowMapper);
	}
}
