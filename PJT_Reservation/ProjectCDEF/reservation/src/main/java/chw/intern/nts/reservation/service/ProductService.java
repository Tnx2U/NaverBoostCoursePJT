package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.DisplayInfoImage;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.dto.ProductImage;
import chw.intern.nts.reservation.dto.ProductPrice;

public interface ProductService {
	public List<Product> getProductsByCategoryId(Integer categoryId, Integer start, Integer limit);
	public int getProductsCount(Integer categoryId);
	public int getProductIdByDisplayInfoId(Integer displayInfoId);
	public DisplayInfo getDisplayInfoById(Integer displayInfoId);
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(Integer displayInfoId);
	public List<ProductImage> getProductImagesByProductId(Integer productId);
	public List<ProductPrice> getProductPricesByProductId(Integer productId);
}
