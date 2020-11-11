package chw.intern.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.ProductDao;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	//readonly는 디비마다 메시지를 받아서 다르게 처리
	@Transactional
	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId, int start, int limit) {
		List<Product> productList;
		if (categoryId == null) {
			productList = productDao.selectAllWithOffset(start, limit);
		} else {
			productList = productDao.selectByCategoryIdWithOffset(categoryId, start, limit);
		}
		return productList;
	}

	@Transactional
	@Override
	public int getProductsCount(Integer categoryId) {
		int totalCount = -1;
		if (categoryId == null) {
			totalCount = productDao.selectAllCount();
		} else {
			totalCount = productDao.selectCountByCategoryId(categoryId);
		}
		return totalCount;
	}
}
