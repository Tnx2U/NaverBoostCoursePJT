package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.Category;

public interface CategoryService {
	public List<Category> getAllCategoriesWithCount();
}
