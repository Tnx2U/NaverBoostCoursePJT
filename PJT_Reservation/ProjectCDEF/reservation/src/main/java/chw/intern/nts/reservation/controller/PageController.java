package chw.intern.nts.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.service.CommentService;

@Controller
public class PageController {
	@Autowired
	CommentService commentService;

	@GetMapping(path = "/main")
	public String getMainPage(ModelMap model) {
		return "main";
	}

	@GetMapping(path = "/detail")
	public String getDetailPage() {
		return "detail";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public String getReviewPage(@PathVariable(name = "displayInfoId", required = true) Integer displayInfoId,
			HttpServletRequest request) {
		
		List<Comment> commentList = commentService.getCommentsByDisplayInfoId(displayInfoId);
		double averageScore = commentService.getAverageScore(commentList);

		request.setAttribute("commentList", commentList);
		request.setAttribute("averageScore", averageScore);

		return "review";
	}
	
	@GetMapping(path = "/reserve/{displayInfoId}")
	public String getReservePage(@PathVariable(name = "displayInfoId", required = true) Integer displayInfoId,
			HttpServletRequest request) {
		
		
		return "reserve";
	}
}
