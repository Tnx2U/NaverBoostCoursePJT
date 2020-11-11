package chw.intern.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.dao.CategoryDao;
import chw.intern.nts.reservation.dto.Category;
import chw.intern.nts.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryApiController {
	@Autowired 
	ReservationService service;
	
	@GetMapping
	public Map<String, Object> categoryList(){
		List<Category> list = service.getAllCategoriesWithCount();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList", list);
		
		return map;
	}
	
//	@GetMapping("/{id}")
//	public Map<String, Object> Category(@PathVariable(name="id") Integer id, HttpServletRequest request){
//		
//	}
	
}
