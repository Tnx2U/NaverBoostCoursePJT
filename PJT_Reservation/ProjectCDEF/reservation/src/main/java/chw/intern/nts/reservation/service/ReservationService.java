package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.ReservationInfo;

public interface ReservationService {
	List<ReservationInfo> getReservationsByEmail(String reservationEmail);
}
