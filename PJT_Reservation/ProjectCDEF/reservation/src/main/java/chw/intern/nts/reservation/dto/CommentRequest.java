package chw.intern.nts.reservation.dto;

import org.springframework.web.multipart.MultipartFile;

public class CommentRequest {
	private MultipartFile attachedImage;
	private String comment;
	private Integer productId;
	private Integer score;
	private Integer reservationInfoId;

	public MultipartFile getAttachedImage() {
		return attachedImage;
	}

	public void setAttachedImage(MultipartFile attachedImage) {
		this.attachedImage = attachedImage;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	@Override
	public String toString() {
		return "CommentRequest [attachedImage=" + attachedImage + ", comment=" + comment + ", productId=" + productId
				+ ", score=" + score + ", reservationInfoId=" + reservationInfoId + "]";
	}
}
