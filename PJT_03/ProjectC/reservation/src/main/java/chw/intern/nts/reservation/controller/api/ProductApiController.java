package chw.intern.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.dto.Category;
import chw.intern.nts.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
//	@Autowired
//	ProductService service;
//
//	@GetMapping
//	public Map<String, Object> categoryList() {
//		List<Product> categoryList = service.getAllCategoriesWithCount();
//		Map<String, Object> map = new HashMap<>();
//		map.put("categoryList", categoryList);
//
//		return map;
//	}
}
