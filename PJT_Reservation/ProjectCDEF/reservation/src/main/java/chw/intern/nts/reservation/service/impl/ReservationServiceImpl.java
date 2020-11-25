package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.ReservationInfoDao;
import chw.intern.nts.reservation.dto.ReservationInfoPrice;
import chw.intern.nts.reservation.dto.ReservationInfo;
import chw.intern.nts.reservation.dto.ReservationParam;
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
			String errorMsg = String.format("Error Occured with params : {reservationEmail : %d} ", reservationEmail);
			LOGGER.error(errorMsg + e.getLocalizedMessage());
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

			ReservationInfo insertedReservationInfo =  reservationInfoDao.selectReservationInfoById(insertedReservationInfoId);
			responseReservationParam = ReservationParam.from(insertedReservationInfo);
			List<ReservationInfoPrice> insertedPriceList = reservationInfoDao.selectReservationInfoPriceByReservationId(insertedReservationInfoId);
			responseReservationParam.setPrices(insertedPriceList);
			
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {reservationParam : %s} ", reservationParam);
			LOGGER.error(errorMsg + e.getLocalizedMessage());
		}

		return responseReservationParam;
	}
}
