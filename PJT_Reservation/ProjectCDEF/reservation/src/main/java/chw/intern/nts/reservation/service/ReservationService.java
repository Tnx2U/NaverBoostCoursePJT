package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.ReservationParam;
import chw.intern.nts.reservation.entity.ReservationInfo;

public interface ReservationService {
	List<ReservationInfo> getReservationsByEmail(String reservationEmail);
	ReservationParam postReservation(ReservationParam reservationParam);
}
