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
import chw.intern.nts.reservation.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	ReservationService service;
	
	@GetMapping(path="/main")
	public String mainPage(ModelMap model) {
		List<Category> categoryList = service.getAllCategories();
		
		model.addAttribute("categoryList", categoryList);
		
		return "main";
	}
	
//	@GetMapping(path="/api/categories")
//	public void allCategories(@ModelAttribute Category category) {
//		System.out.println(category);
//	}
}
