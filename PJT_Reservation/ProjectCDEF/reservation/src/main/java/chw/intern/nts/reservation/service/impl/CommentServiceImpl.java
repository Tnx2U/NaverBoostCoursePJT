package chw.intern.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
//	@Autowired
//	CommentDao commentDao;
	
	@Override
	public List<Comment> getCommentsByProductId(int productId) {
		
		return null;
	}
}
