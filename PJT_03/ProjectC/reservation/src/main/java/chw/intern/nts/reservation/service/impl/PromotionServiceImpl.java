package chw.intern.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.PromotionDao;
import chw.intern.nts.reservation.dto.Promotion;
import chw.intern.nts.reservation.service.PromotionService;

public class PromotionServiceImpl implements PromotionService {
	@Autowired
	PromotionDao promotionDao;

	@Override
	@Transactional
	public List<Promotion> getAllPromotionWithImgUrl() {
		List<Promotion> promotionList = promotionDao.selectAllWithImgUrl();
		return promotionList;
	}
}
