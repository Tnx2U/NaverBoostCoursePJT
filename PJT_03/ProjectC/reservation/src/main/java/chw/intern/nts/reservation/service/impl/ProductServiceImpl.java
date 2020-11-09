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

	@Transactional
	@Override
	public List<Product> getProductsByCategoryId(int categoryId, int start, int limit) {
		List<Product> productList = productDao.selectByCategoryId(categoryId, start, limit);
		return productList;
	}
}
