package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.PromotionDao;
import chw.intern.nts.reservation.dto.Promotion;
import chw.intern.nts.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	PromotionDao promotionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Promotion> getAllPromotionWithImgUrl() {
		List<Promotion> promotionList = Collections.emptyList();

		try {
			promotionList = promotionDao.selectAllWithImgUrl();
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {} ");
			LOGGER.error(errorMsg+e.getLocalizedMessage());
		}
		return promotionList;
	}
}
