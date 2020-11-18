package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.CommentDao;
import chw.intern.nts.reservation.dao.DisplayInfoDao;
import chw.intern.nts.reservation.dao.ProductDao;
import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.CommentImage;
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
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

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
	public DisplayInfoResponse getDisplayInfoResponseByDisplayInfoId(Integer displayInfoId) {
		// 리턴객체 초기화
		// TODO autowired를 사용하거나 다른 맵퍼로 할당할 수 없는지 조사
		DisplayInfoResponse displayInfoResponse = DisplayInfoResponse.getInstance();

		// 리턴객체 의존성 객체 초기화
		int productId = -1;
		double averageScore = 0;
		DisplayInfo displayInfo = null;
		DisplayInfoImage displayInfoImage = null;
		List<ProductImage> productImageList = Collections.emptyList();
		List<ProductPrice> ProductPriceList = Collections.emptyList();
		List<Comment> commentList = Collections.emptyList();
		List<CommentImage> commentImages = Collections.emptyList();

		try {
			// dao 호출 및 변수 바인딩
			productId = displayInfoDao.selectProductIdById(displayInfoId);
			displayInfo = displayInfoDao.selectById(displayInfoId);
			displayInfoImage = displayInfoDao.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
			productImageList = productDao.selectProductImagesById(productId);
			ProductPriceList = productDao.selectProductPricesByProductId(productId);
			commentList = commentDao.selectAllByDisplayInfoId(displayInfoId);
			for (Comment comment : commentList) {
				Integer commentId = comment.getCommentId();
				commentImages = commentDao.selectAllByCommentId(commentId);
				comment.setCommentImages(commentImages);
			}
			averageScore = commentService.getAverageScore(commentList);

			// 리턴 객체에 의존성 주입
			displayInfoResponse.setAverageScore(averageScore);
			displayInfoResponse.setComments(commentList);
			displayInfoResponse.setDisplayInfo(displayInfo);
			displayInfoResponse.setDisplayInfoImage(displayInfoImage);
			displayInfoResponse.setProductImages(productImageList);
			displayInfoResponse.setProductPrices(ProductPriceList);
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {displayInfoId : %d}", displayInfoId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return displayInfoResponse;
	}
}
