package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chw.intern.nts.reservation.dao.CommentDao;
import chw.intern.nts.reservation.dao.DisplayInfoDao;
import chw.intern.nts.reservation.dao.ProductDao;
import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.DisplayInfoImage;
import chw.intern.nts.reservation.dto.DisplayInfoResponse;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.dto.ProductImage;
import chw.intern.nts.reservation.dto.ProductPrice;
import chw.intern.nts.reservation.service.CommentService;
import chw.intern.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	@Autowired
	DisplayInfoDao displayInfoDao;

	@Autowired
	CommentDao commentDao;

	@Autowired
	CommentService commentService;

	@Transactional(readOnly = true)
	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId, Integer start, Integer limit) {
		List<Product> productList = Collections.emptyList();
		try {
			if (categoryId == null) {
				productList = productDao.selectAllWithOffset(start, limit);
			} else {
				productList = productDao.selectByCategoryIdWithOffset(categoryId, start, limit);
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {categoryId : {}, start : {}, limit: {}} \r\n{}", categoryId,
					start, limit, e.getLocalizedMessage());
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
			LOGGER.error("Error Occured with params : {categoryId : {}} \r\n{}", categoryId, e.getLocalizedMessage());
		}

		return totalCount;
	}

	@Transactional(readOnly = true)
	@Override
	public DisplayInfoResponse getDisplayInfoResponseByDisplayInfoId(Integer displayInfoId) {
		// 리턴객체 초기화
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		// 리턴객체 의존성 객체 초기화
		int productId = -1;
		double averageScore = 0;
		DisplayInfo displayInfo = null;
		DisplayInfoImage displayInfoImage = null;
		List<ProductImage> productImageList = Collections.emptyList();
		List<ProductPrice> ProductPriceList = Collections.emptyList();
		List<Comment> commentList = Collections.emptyList();

		try {
			// dao 호출 및 변수 바인딩
			productId = displayInfoDao.selectProductIdById(displayInfoId);
			displayInfo = displayInfoDao.selectById(displayInfoId);
			displayInfoImage = displayInfoDao.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
			productImageList = productDao.selectProductImagesById(productId);
			ProductPriceList = productDao.selectProductPricesByProductId(productId);
			commentList = commentService.getCommentsByDisplayInfoId(displayInfoId);
			averageScore = commentService.getAverageScore(commentList);

			// 리턴 객체에 의존성 주입
			displayInfoResponse.setAverageScore(averageScore);
			displayInfoResponse.setComments(commentList);
			displayInfoResponse.setDisplayInfo(displayInfo);
			displayInfoResponse.setDisplayInfoImage(displayInfoImage);
			displayInfoResponse.setProductImages(productImageList);
			displayInfoResponse.setProductPrices(ProductPriceList);
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {displayInfoId : {}} \r\n{}", displayInfoId,
					e.getLocalizedMessage());
		}
		return displayInfoResponse;
	}

	@Transactional(readOnly = true)
	@Override
	public DisplayInfo getDisplayInfoById(Integer displayInfoId) {
		DisplayInfo displayInfo = null;

		try {
			displayInfo = displayInfoDao.selectById(displayInfoId);
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {displayInfoId : {}} \r\n{}", displayInfoId,
					e.getLocalizedMessage());
		}

		return displayInfo;
	}

	@Transactional(readOnly = true)
	@Override
	public String getDescriptionByProductId(Integer productId) {
		String productDescription = null;

		try {
			productDescription = productDao.selectDescriptionByProductId(productId);
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {productId : {}} \r\n{}", productId, e.getLocalizedMessage());
		}

		return productDescription;
	}
}
