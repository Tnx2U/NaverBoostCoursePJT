package chw.intern.nts.reservation.dao;

import static chw.intern.nts.reservation.dao.sql.PromotionDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	// 자동으로 dto 객체에 결과를 맵핑시켜줌, sql이랑 자바의 네이밍 컨벤션도 자동으로 해줌
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Promotion> selectAllWithImgUrl() {
		return jdbcTemplate.query(SELECT_ALL_WITH_IMG_URL, Collections.emptyMap(), rowMapper);
	}
}
