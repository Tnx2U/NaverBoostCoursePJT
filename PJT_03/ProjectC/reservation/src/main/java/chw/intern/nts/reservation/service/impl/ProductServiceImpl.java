package chw.intern.nts.reservation.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.ProductDao;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	// readonly는 디비마다 메시지를 받아서 다르게 처리
	@Transactional(readOnly = true)
	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId, int start, int limit) {
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
}
