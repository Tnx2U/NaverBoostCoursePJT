package chw.intern.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.dto.Category;
import chw.intern.nts.reservation.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;

	@GetMapping
	public Map<String, Object> getCategories() {
		List<Category> CategoryResponse = categoryService.getAllCategoriesWithCount();
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("items", CategoryResponse);

		return responseMap;
	}
}
