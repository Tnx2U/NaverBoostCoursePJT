package chw.intern.nts.reservation.dao;

import static chw.intern.nts.reservation.dao.ProductDaoSqls.SELECT_PRODUCT_IMAGES_BY_ID;
import static chw.intern.nts.reservation.dao.PromotionDaoSqls.SELECT_ALL_WITH_IMG_URL;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.ReservationInfo;
import static chw.intern.nts.reservation.dao.ReservationInfoDaoSqls.*;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);

	public ReservationInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationInfo> selectAllByEmail(String reservationEmail) {
		Map<String, String> params = Collections.singletonMap("reservationEmail", reservationEmail);

		return jdbcTemplate.query(SELECT_ALL_BY_EMAIL, params, rowMapper);
	}
}
