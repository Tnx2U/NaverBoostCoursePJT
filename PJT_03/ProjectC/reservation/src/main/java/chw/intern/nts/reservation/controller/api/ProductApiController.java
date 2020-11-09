package chw.intern.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> productList(@RequestParam(name="categoryId", required=false)int categoryId
	, @RequestParam(name="start", required=false, defaultValue="0")int start){
		final int LIMIT = 4;
		List<Product> ProductResponse = productService.getProductsByCategoryId(categoryId, start, LIMIT);
		int totalCount = -1;
		Map<String, Object> map = new HashMap<>();
		map.put("items", ProductResponse);
		map.put("totalCount", totalCount);

		return map;
	}
}
