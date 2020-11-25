package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.ReservationInfo;
import chw.intern.nts.reservation.dto.ReservationParam;

public interface ReservationService {
	List<ReservationInfo> getReservationsByEmail(String reservationEmail);
	ReservationParam postReservation(ReservationParam reservationParam);
}
