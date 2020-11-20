package chw.intern.nts.reservation.service;

import java.util.List;

import chw.intern.nts.reservation.dto.Comment;

public interface CommentService {
	public List<Comment> getCommentsByDisplayInfoId(Integer displayInfoId);
	public double getAverageScore(List<Comment> commentList);
}
