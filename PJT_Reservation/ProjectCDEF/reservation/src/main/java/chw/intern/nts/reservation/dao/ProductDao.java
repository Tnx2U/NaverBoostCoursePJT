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
import chw.intern.nts.reservation.dto.ProductImage;
import chw.intern.nts.reservation.dto.ProductPrice;

import static chw.intern.nts.reservation.dao.ProductDaoSqls.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	// 자동으로 dto 객체에 결과를 맵핑시켜줌, sql이랑 자바의 네이밍 컨벤션도 자동으로 해줌
	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	
	
	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectByCategoryIdWithOffset(Integer categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		return jdbcTemplate.query(SELECT_BY_CATEGORY_ID_WITH_OFFSET, params, productRowMapper);
	}

	public List<Product> selectAllWithOffset(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);

		return jdbcTemplate.query(SELECT_ALL_WITH_OFFSET, params, productRowMapper);
	}

	public int selectAllCount() {
		return jdbcTemplate.queryForObject(SELECT_ALL_COUNT, Collections.emptyMap(), Integer.class);
	}

	public int selectCountByCategoryId(Integer categoryId) {
		Map<String, Integer> params = Collections.singletonMap("categoryId", categoryId);
		
		return jdbcTemplate.queryForObject(SELECT_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}

	public List<ProductImage> selectProductImagesById(Integer productId) {
		Map<String, Integer> params = Collections.singletonMap("productId", productId);

		return jdbcTemplate.query(SELECT_PRODUCT_IMAGES_BY_ID, params, productImageRowMapper);
	}

	public List<ProductPrice> selectProductPricesByProductId(Integer productId) {
		Map<String, Integer> params = Collections.singletonMap("productId", productId);

		return jdbcTemplate.query(SELECT_PRODUCT_PRICES_BY_PRODUCT_ID, params, productPriceRowMapper);
	}
}
