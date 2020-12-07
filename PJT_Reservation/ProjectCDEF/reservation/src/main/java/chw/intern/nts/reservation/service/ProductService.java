package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.DisplayInfoResponse;
import chw.intern.nts.reservation.dto.Product;

public interface ProductService {
	public List<Product> getProductsByCategoryId(Integer categoryId, Integer start, Integer limit);
	public int getProductsCount(Integer categoryId);
	public DisplayInfoResponse getDisplayInfoResponseByDisplayInfoId(Integer displayInfoId);
	public DisplayInfo getDisplayInfoById(Integer displayInfoId);
	public String getDescriptionByProductId(Integer productId);
}
