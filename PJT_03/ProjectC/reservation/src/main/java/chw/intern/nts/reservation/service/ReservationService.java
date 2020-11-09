package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.Category;

public interface ReservationService {
	public List<Category> getAllCategories();
	public List<Category> getAllCategoriesWithCount();
}
