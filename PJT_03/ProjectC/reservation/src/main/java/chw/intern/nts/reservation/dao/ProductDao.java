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

import chw.intern.nts.reservation.dto.Product;
import static chw.intern.nts.reservation.dao.ProductDaoSqls.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	// 자동으로 dto 객체에 결과를 맵핑시켜줌, sql이랑 자바의 네이밍 컨벤션도 자동으로 해줌
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Product> selectByCategoryIdWithOffset(Integer categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbcTemplate.query(SELECT_BY_CATEGORY_ID_WITH_OFFSET, params ,rowMapper);
	}

	public List<Product> selectAllWithOffset(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbcTemplate.query(SELECT_ALL_WITH_OFFSET, params ,rowMapper);
	}

	public int selectAllCount() {
		return jdbcTemplate.queryForObject(SELECT_ALL_COUNT, Collections.emptyMap(), Integer.class);
	}

	public int selectCountByCategoryId(Integer categoryId) {
		Map<String, Integer> params = Collections.singletonMap("categoryId", categoryId);
		return jdbcTemplate.queryForObject(SELECT_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}
}