package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.ReservationInfoDao;
import chw.intern.nts.reservation.dto.ReservationParam;
import chw.intern.nts.reservation.entity.ReservationInfo;
import chw.intern.nts.reservation.entity.ReservationInfoPrice;
import chw.intern.nts.reservation.service.ProductService;
import chw.intern.nts.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ProductService productService;

	@Autowired
	ReservationInfoDao reservationInfoDao;

	@Transactional(readOnly = true)
	@Override
	public List<ReservationInfo> getReservationsByEmail(String reservationEmail) {
		List<ReservationInfo> reservationInfoList = Collections.emptyList();

		try {
			reservationInfoList = reservationInfoDao.selectAllByEmail(reservationEmail);
			for (ReservationInfo reservationInfo : reservationInfoList) {
				int targetDisplayInfoId = reservationInfo.getDisplayInfoId();
				reservationInfo.setDisplayInfo(productService.getDisplayInfoById(targetDisplayInfoId));
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {reservationEmail : {}} \r\n{}", reservationEmail,
					e.getLocalizedMessage());
		}

		return reservationInfoList;
	}

	@Transactional(readOnly = false)
	@Override
	public ReservationParam postReservation(ReservationParam reservationParam) {
		ReservationParam responseReservationParam = null;
		int insertedReservationInfoId = -1;
		int insertedReservationInfoPriceId = -1;
		// 정적 팩토리 메서드 사용
		ReservationInfo reservationInfo = ReservationInfo.from(reservationParam);

		try {
			insertedReservationInfoId = reservationInfoDao.insertReservationInfo(reservationInfo);
			if (insertedReservationInfoId == -1) {
				throw new Exception("ReservationInfoDao.insertReservationInfoPrice has no return value ");
			}

			for (ReservationInfoPrice price : reservationParam.getPrices()) {
				price.setReservationInfoId(insertedReservationInfoId);
				insertedReservationInfoPriceId = reservationInfoDao.insertReservationInfoPrice(price);
				if (insertedReservationInfoPriceId == -1) {
					throw new Exception("ReservationInfoDao.insertReservationInfo has no return value ");
				}
			}

			responseReservationParam = getReservationsInfoWithPricesById(insertedReservationInfoId);

		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {reservationParam : {}} \r\n{}", reservationParam,
					e.getLocalizedMessage());
		}

		return responseReservationParam;
	}

	@Transactional(readOnly = false)
	@Override
	public ReservationParam putCancelFlag(Integer reservationInfoId) {
		ReservationParam responseReservationParam = null;
		int updatedRow = -1;

		try {
			updatedRow = reservationInfoDao.updateCancelFlagByReservationInfoId(reservationInfoId);

			if (updatedRow == 0) {
				throw new Exception("ReservationInfoDao.updateCancelFlagByReservationInfoId has no return value ");
			}

			responseReservationParam = getReservationsInfoWithPricesById(reservationInfoId);
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {reservationInfoId : {}} \r\n{}", reservationInfoId,
					e.getLocalizedMessage());
		}

		return responseReservationParam;
	}

	@Transactional(readOnly = true)
	@Override
	public ReservationParam getReservationsInfoWithPricesById(Integer reservationInfoId) {
		ReservationParam responseReservationParam = null;

		try {
			ReservationInfo updatedReservationInfo = reservationInfoDao.selectReservationInfoById(reservationInfoId);
			responseReservationParam = ReservationParam.from(updatedReservationInfo);
			List<ReservationInfoPrice> priceList = reservationInfoDao
					.selectReservationInfoPriceByReservationId(reservationInfoId);
			responseReservationParam.setPrices(priceList);
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {reservationInfoId : {}} \r\n{}", reservationInfoId,
					e.getLocalizedMessage());
		}

		return responseReservationParam;
	}
}
