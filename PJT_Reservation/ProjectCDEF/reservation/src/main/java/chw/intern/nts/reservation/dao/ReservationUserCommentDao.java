package chw.intern.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.entity.ReservationUserComment;
import chw.intern.nts.reservation.entity.ReservationUserCommentImage;

@Repository
public class ReservationUserCommentDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertReservationUserCommentAction;
	private SimpleJdbcInsert insertReservationUserCommentImageAction;

	public ReservationUserCommentDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertReservationUserCommentAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		this.insertReservationUserCommentImageAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");
	}

	public int insertReservationUserComment(ReservationUserComment reservationUserComment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserComment);

		return insertReservationUserCommentAction.executeAndReturnKey(params).intValue();
	}

	public int insertReservationUserCommentImage(ReservationUserCommentImage reservationUserCommentImage) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserCommentImage);

		return insertReservationUserCommentImageAction.executeAndReturnKey(params).intValue();
	}
}
