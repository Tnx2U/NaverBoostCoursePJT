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
		// 데이터 전처리
		List<Comment> commentList = commentService.getCommentsByDisplayInfoId(displayInfoId);

		double averageScore = 0;
		double graphValueWidth = 0;
		for (Comment comment : commentList) {
			averageScore += comment.getScore();
		}

		if (averageScore != 0) {
			averageScore = Math.round(averageScore / commentList.size() * 10) / 10.0;
			graphValueWidth = averageScore * 20;
		}

		request.setAttribute("commentList", commentList);
		request.setAttribute("averageScore", averageScore);
		request.setAttribute("graphValueWidth", graphValueWidth);

		return "review";
	}
}
