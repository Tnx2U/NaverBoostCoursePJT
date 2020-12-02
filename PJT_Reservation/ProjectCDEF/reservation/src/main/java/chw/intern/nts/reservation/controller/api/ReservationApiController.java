package chw.intern.nts.reservation.controller.api;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.ReservationParam;
import chw.intern.nts.reservation.entity.ReservationInfo;
import chw.intern.nts.reservation.service.CommentService;
import chw.intern.nts.reservation.service.ReservationService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	CommentService commentService;

	@GetMapping
	public Map<String, Object> getReservations(
			@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {
		Map<String, Object> responseMap = new HashMap<>();

		List<ReservationInfo> reservationInfoList = reservationService.getReservationsByEmail(reservationEmail);
		responseMap.put("reservations", reservationInfoList);
		responseMap.put("size", reservationInfoList.size());

		return responseMap;
	}

	@PostMapping
	public ReservationParam postReservation(@RequestBody ReservationParam reservationParam) {
		ReservationParam responseReservation = reservationService.postReservation(reservationParam);

		return responseReservation;
	}

	@PostMapping(path = "/{reservationInfoId}/comments")
	public Comment postComment(@RequestParam("file") MultipartFile file) {

		Comment responseComment = commentService.postComment(file);

		return responseComment;
	}

	@PutMapping("/{reservationId}")
	public ReservationParam putCancelFlag(
			@PathVariable(name = "reservationId", required = true) Integer reservationId) {
		ReservationParam responseReservation = reservationService.putCancelFlag(reservationId);
		return responseReservation;
	}
}
