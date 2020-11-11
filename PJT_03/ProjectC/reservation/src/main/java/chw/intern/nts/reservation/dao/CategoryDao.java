package chw.intern.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.Category;
import static chw.intern.nts.reservation.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	// 자동으로 dto 객체에 결과를 맵핑시켜줌, sql이랑 자바의 네이밍 컨벤션도 자동으로 해줌
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Category selectById(Integer id) {
		try {
			Map<String, ?> params = Collections.singletonMap("id", id);
			return jdbcTemplate.queryForObject(SELECT_BY_ID, params, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Category> selectAllWithCount() {
		return jdbcTemplate.query(SELECT_ALL_WITH_COUNT, Collections.emptyMap(), rowMapper);
	}
}
