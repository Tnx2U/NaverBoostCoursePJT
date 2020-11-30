package chw.intern.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.dto.DisplayInfoResponse;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> getProducts(@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "start", defaultValue = "0") int start,
			@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {
		List<Product> productResponse = productService.getProductsByCategoryId(categoryId, start, limit);
		int totalCount = productService.getProductsCount(categoryId);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("items", productResponse);
		responseMap.put("totalCount", totalCount);

		return responseMap;
	}

	@GetMapping("/{displayInfoId}")
	public DisplayInfoResponse getProductsByDisplayInfoId(
			@PathVariable(name = "displayInfoId", required = true) Integer displayInfoId) {
		DisplayInfoResponse displayInfoResponse = productService.getDisplayInfoResponseByDisplayInfoId(displayInfoId);

		return displayInfoResponse;
	}
}
