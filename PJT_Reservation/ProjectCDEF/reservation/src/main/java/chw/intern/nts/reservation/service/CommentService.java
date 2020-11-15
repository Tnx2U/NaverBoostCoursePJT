package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.Comment;

public interface CommentService {
	public List<Comment> getCommentsByProductId(int productId);
}
