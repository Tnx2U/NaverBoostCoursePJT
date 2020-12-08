package chw.intern.nts.reservation.entity;

import java.sql.Date;

import chw.intern.nts.reservation.dto.CommentRequest;

public class ReservationUserComment {
	private Integer id;
	private Integer productId;
	private Integer reservationInfoId;
	private Integer score;
	private String comment;
	private Date createDate;
	private Date modifyDate;

	public ReservationUserComment(Integer productId, Integer reservationInfoId, Integer score, String comment) {
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
		this.comment = comment;
		this.createDate = new Date(System.currentTimeMillis());
		this.modifyDate = new Date(System.currentTimeMillis());
	}

	public static ReservationUserComment from(CommentRequest commentRequest) {
		ReservationUserComment reservatonUserComment = new ReservationUserComment(commentRequest.getProductId(),
				commentRequest.getReservationInfoId(), commentRequest.getScore(), commentRequest.getComment());
		return reservatonUserComment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
