package chw.intern.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.CategoryDao;
import chw.intern.nts.reservation.dto.Category;
import chw.intern.nts.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	CategoryDao categoryDao;

	@Override
	// 읽기만 하는 함수이므로 read-only형태로 커넥션을 사용하게 해주는 어노테이션
	@Transactional
	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryDao.selectAll();
		return categoryList;
	}

	@Override
	@Transactional
	public List<Category> getAllCategoriesWithCount() {
		List<Category> categoryList = categoryDao.selectAllWithCount();
		return categoryList;
	}
}
