package chw.intern.nts.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chw.intern.nts.reservation.dao.CommentDao;
import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.CommentImage;
import chw.intern.nts.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByDisplayInfoId(Integer displayInfoId) {
		List<Comment> commentList = Collections.emptyList();
		try {
			commentList = commentDao.selectAllByDisplayInfoId(displayInfoId);
			for (Comment comment : commentList) {
				Integer commentId = comment.getCommentId();
				List<CommentImage> commentImages = commentDao.selectAllByCommentId(commentId);
				comment.setCommentImages(commentImages);
			}
		} catch (Exception e) {
			String errorMsg = String.format("Error Occured with params : {displayInfoId : %d}", displayInfoId);
			System.err.println(errorMsg + e.getLocalizedMessage());
		}

		return commentList;
	}

	@Override
	public double getAverageScore(List<Comment> commentList) {
		double averageScore = 0;
		// TODO stream이나 람다를 사용해 의도가 드러나면서도 좀 더 깔끔하게 할 수 없는지 공부
		for (Comment comment : commentList) {
			averageScore += comment.getScore();
		}
		if (averageScore != 0) {
			averageScore /= commentList.size();
		}

		averageScore = Math.round(averageScore*10)/10.0;
		
		return averageScore;
	}
}
