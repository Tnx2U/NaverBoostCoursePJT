package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.CategoryDao;
import chw.intern.nts.reservation.dto.Category;
import chw.intern.nts.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	CategoryDao categoryDao;

	@Override
	// 읽기만 하는 함수이므로 read-only형태로 커넥션을 사용하게 해주는 어노테이션
	@Transactional(readOnly = true)
	public List<Category> getAllCategoriesWithCount() {
		List<Category> categoryList = Collections.emptyList();
		try {
			categoryList = categoryDao.selectAllWithCount();
			return categoryList;
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {} ");
			LOGGER.error(errorMsg + e.getLocalizedMessage());
		}
		return categoryList;
	}
}
