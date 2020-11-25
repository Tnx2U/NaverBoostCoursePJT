package chw.intern.nts.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.ReservationInfoPrice;
import chw.intern.nts.reservation.dto.ReservationInfo;

import static chw.intern.nts.reservation.dao.ReservationInfoDaoSqls.*;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertReservationInfoAction;
	private SimpleJdbcInsert insertPriceAction;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);

	public ReservationInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertReservationInfoAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
		this.insertPriceAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
	}

	public List<ReservationInfo> selectAllByEmail(String reservationEmail) {
		Map<String, String> params = Collections.singletonMap("reservationEmail", reservationEmail);

		return jdbcTemplate.query(SELECT_ALL_BY_EMAIL, params, rowMapper);
	}

	public int insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);

		return insertReservationInfoAction.executeAndReturnKey(params).intValue();
	}

	public int insertReservationInfoPrice(ReservationInfoPrice reservationInfoPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);

		return insertPriceAction.executeAndReturnKey(params).intValue();
	}
}
