package chw.intern.nts.reservation.dao;


import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.dto.Category;
import static chw.intern.nts.reservation.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;

//저장소라는 개념으로 repository 어노테이션 지정
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	// 자동으로 dto 객체에 결과를 맵핑시켜줌, sql이랑 자바의 네이밍 컨벤션도 자동으로 해줌
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Category> selectAll(){
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
