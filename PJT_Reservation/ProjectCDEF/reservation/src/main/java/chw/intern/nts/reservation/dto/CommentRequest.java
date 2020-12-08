package chw.intern.nts.reservation.dto;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class CommentRequest {	
	@NotNull
	private String comment;
	@NotNull
	private Integer productId;
	@NotNull
	private Integer score;
	private MultipartFile attachedImage;

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

	@Override
	public String toString() {
		return "CommentRequest [attachedImage=" + attachedImage + ", comment=" + comment + ", productId=" + productId
				+ ", score=" + score + "]";
	}
}
