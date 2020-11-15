package chw.intern.nts.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static chw.intern.nts.reservation.dao.DisplayInfoDaoSqls.*;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public DisplayInfoDao(DataSource dataSource) {
			this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}

	public int selectProductIdById(Integer displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_ID_BY_ID, params, Integer.class);
	}
}
