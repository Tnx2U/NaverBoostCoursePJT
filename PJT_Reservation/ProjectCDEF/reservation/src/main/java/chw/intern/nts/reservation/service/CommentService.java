package chw.intern.nts.reservation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import chw.intern.nts.reservation.dto.Comment;

public interface CommentService {
	public List<Comment> getCommentsByDisplayInfoId(Integer displayInfoId);
	public double getAverageScore(List<Comment> commentList);
	public Comment postComment(MultipartFile attachedImage, String comment, Integer productId, Integer score, Integer reservationInfoId);
	public Comment getCommentById(Integer commentId);
	public void upLoadImage(MultipartFile attachedImage, String saveFileName) throws IOException;
}
