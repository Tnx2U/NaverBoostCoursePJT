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
		// 입력받은 객체를 수정하여 리턴 or 입력은 입력대로 하고, 다시 쿼리 날려서 객체 받아옴

		ReservationParam responseReservation = null;
		int insertedReservationInfoId = -1;
		int insertedReservationInfoPriceId = -1;

		// 정적 팩토리 메서드 사용
		ReservationInfo reservationInfo = ReservationInfo.from(reservationParam);

		try {
			// reservation객체 전처리

			// reservation info 삽입
			insertedReservationInfoId = reservationInfoDao.insertReservationInfo(reservationInfo);
//			if (insertedReservationInfoId == -1) {
//				// 예외처리
//				throw new Exception("ReservationInfoDao.insertReservationInfoPrice has no return value ");
//			}

			// 반복돌며 reservation info price 삽입
			for (ReservationInfoPrice price : reservationParam.getPrices()) {
				price.setReservationInfoId(insertedReservationInfoId);
				insertedReservationInfoPriceId = reservationInfoDao.insertReservationInfoPrice(price);
//				if (insertedReservationInfoPriceId == -1) {
//					// 예외처리
//					throw new Exception("ReservationInfoDao.insertReservationInfo has no return value ");
//				}
			}

			// insert된 결과를 다시 select하여 사용자 입력값 리턴

		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {reservationParam : %s} ", reservationParam);
			LOGGER.error(errorMsg + e.getLocalizedMessage());
		}

		return responseReservation;
	}
}
