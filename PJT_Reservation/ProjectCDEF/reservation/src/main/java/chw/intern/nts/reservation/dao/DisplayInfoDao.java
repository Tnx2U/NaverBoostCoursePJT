package chw.intern.nts.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.DisplayInfoImage;

import static chw.intern.nts.reservation.dao.DisplayInfoDaoSqls.*;

@Repository
public class DisplayInfoDao {
	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private NamedParameterJdbcTemplate jdbcTemplate;

	public DisplayInfoDao(DataSource dataSource) {
			this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}

	public int selectProductIdById(Integer displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_ID_BY_ID, params, Integer.class);
	}

	public DisplayInfo selectById(Integer displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		
		return jdbcTemplate.queryForObject(SELECT_BY_ID, params, displayInfoRowMapper);
	}

	public DisplayInfoImage selectDisplayInfoImageByDisplayInfoId(Integer displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		
		return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID, params, displayInfoImageRowMapper);
	}
}
