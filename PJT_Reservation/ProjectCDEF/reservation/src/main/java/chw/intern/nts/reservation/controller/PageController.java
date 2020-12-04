package chw.intern.nts.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.service.CommentService;
import chw.intern.nts.reservation.service.ProductService;

@Controller
public class PageController {
	@Autowired
	CommentService commentService;

	@Autowired
	ProductService productService;

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

	@GetMapping(path = "/reserve")
	public String getReservePage(@RequestParam(name = "displayInfoId", required = true) Integer displayInfoId,
			HttpServletRequest request) {

		return "reserve";
	}

	@GetMapping(path = "/bookingLogin")
	public String getBookingLoginPage() {

		return "bookingLogin";
	}

	@GetMapping(path = "/myReservation")
	public String getMyReservationPage(
			@RequestParam(name = "reservationEmail", required = true) String reservationEmail, HttpSession session) {

		session.setAttribute("reservationEmail", reservationEmail);
		session.setMaxInactiveInterval(10 * 60);

		return "myReservation";
	}

	@GetMapping(path = "/reviewWrite")
	public String getMyReservationPage(@RequestParam(name = "productId", required = true) Integer productId,
			HttpServletRequest request) {

		String productDescription = productService.getDescriptionByProductId(productId);
		request.setAttribute("productDescription", productDescription);
		
		return "reviewWrite";
	}
}
