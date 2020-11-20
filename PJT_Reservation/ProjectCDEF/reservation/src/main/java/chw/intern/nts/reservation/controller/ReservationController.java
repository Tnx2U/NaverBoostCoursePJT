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
public class ReservationController {
	static final int MAGNIFIC_FOR_STAR_TO_PERCENT = 20;
	@Autowired
	CommentService commentService;

	@GetMapping(path = "/main")
	public String mainPage(ModelMap model) {
		return "main";
	}

	@GetMapping(path = "/detail")
	public String detailPage() {
		return "detail";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public String reviewPage(@PathVariable(name = "displayInfoId", required = true) Integer displayInfoId,
			HttpServletRequest request) {
		
		List<Comment> commentList = commentService.getCommentsByDisplayInfoId(displayInfoId);
		double averageScore = commentService.getAverageScore(commentList);
		double graphValueWidth = averageScore * MAGNIFIC_FOR_STAR_TO_PERCENT;

		request.setAttribute("commentList", commentList);
		request.setAttribute("averageScore", averageScore);
		request.setAttribute("graphValueWidth", graphValueWidth);

		return "review";
	}
}
