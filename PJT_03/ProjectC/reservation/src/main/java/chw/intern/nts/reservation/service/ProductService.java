package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.Product;

public interface ProductService {
	public List<Product> getProductsByCategoryId(Integer categoryId, int start, int limit);
	public int getProductsCount(Integer categoryId);
}
