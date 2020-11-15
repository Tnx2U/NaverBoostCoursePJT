package chw.intern.nts.reservation.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.DisplayInfoDao;
import chw.intern.nts.reservation.dao.ProductDao;
import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.DisplayInfoImage;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.dto.ProductImage;
import chw.intern.nts.reservation.dto.ProductPrice;
import chw.intern.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	@Autowired
	DisplayInfoDao displayInfoDao;

	// readonly는 디비마다 메시지를 받아서 다르게 처리
	@Transactional(readOnly = true)
	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId, Integer start, Integer limit) {
		List<Product> productList = null;
		try {
			if (categoryId == null) {
				productList = productDao.selectAllWithOffset(start, limit);
			} else {
				productList = productDao.selectByCategoryIdWithOffset(categoryId, start, limit);
			}
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {categoryId : %d, start : %d, limit: %d}",
					categoryId, start, limit);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return productList;
	}

	@Transactional(readOnly = true)
	@Override
	public int getProductsCount(Integer categoryId) {
		int totalCount = -1;
		try {
			if (categoryId == null) {
				totalCount = productDao.selectAllCount();
			} else {
				totalCount = productDao.selectCountByCategoryId(categoryId);
			}
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {categoryId : %d}", categoryId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return totalCount;
	}

	@Transactional(readOnly = true)
	@Override
	public int getProductIdByDisplayInfoId(Integer displayInfoId) {
		int productId = -1;
		try {
			productId = displayInfoDao.selectProductIdById(displayInfoId);
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {displayInfoId : %d}", displayInfoId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}
		return productId;
	}

	@Transactional(readOnly = true)
	@Override
	public DisplayInfo getDisplayInfoById(Integer displayInfoId) {
		DisplayInfo displayInfo = null;

		try {
			displayInfo = displayInfoDao.selectById(displayInfoId);
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {displayInfoId : %d}", displayInfoId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return displayInfo;
	}

	@Transactional(readOnly = true)
	@Override
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(Integer displayInfoId) {
		DisplayInfoImage displayInfoImage = null;
		
		try {
			displayInfoImage = displayInfoDao.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {displayInfoId : %d}", displayInfoId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return displayInfoImage;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductImage> getProductImagesByProductId(Integer productId) {
		List<ProductImage> productImageList = null;
		
		try {
			productImageList = productDao.selectProductImagesById(productId);
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {productId : %d}", productId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return productImageList;
	}

	@Override
	public List<ProductPrice> getProductPricesByProductId(Integer productId) {
		List<ProductPrice> ProductPriceList = null;
		
		try {
			ProductPriceList = productDao.selectProductPricesByProductId(productId);
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {productId : %d}", productId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return ProductPriceList;
	}
}
