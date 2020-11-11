package chw.intern.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chw.intern.nts.reservation.dto.Category;

@Controller
public class ReservationController {
	@GetMapping(path="/main")
	public String mainPage(ModelMap model) {
		return "main";
	}
}