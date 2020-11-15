package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.Product;

public interface ProductService {
	public List<Product> getProductsByCategoryId(Integer categoryId, Integer start, Integer limit);
	public int getProductsCount(Integer categoryId);
	public int getProductIdByDisplayInfoId(Integer displayInfoId);
	public DisplayInfo getDisplayInfoById(Integer displayInfoId);
}
