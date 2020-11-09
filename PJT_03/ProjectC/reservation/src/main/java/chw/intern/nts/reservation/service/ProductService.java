package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.Product;

public interface ProductService {
	public List<Product> getProductsByCategoryId(int categoryId, int start, int limit);
}
