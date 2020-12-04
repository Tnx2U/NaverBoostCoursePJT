package chw.intern.nts.reservation.entity;

public class ReservationUserCommentImage {
	private Integer id;
	private Integer reservationInfoId;
	private Integer reservationUserCommentId;
	private Integer fileId;

	public ReservationUserCommentImage(Integer reservationInfoId, Integer reservationUserCommentId, Integer fileId) {
		this.reservationInfoId = reservationInfoId;
		this.reservationUserCommentId = reservationUserCommentId;
		this.fileId = fileId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(Integer reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
}
