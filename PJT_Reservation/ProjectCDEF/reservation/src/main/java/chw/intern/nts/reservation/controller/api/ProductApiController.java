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

import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.DisplayInfo;
import chw.intern.nts.reservation.dto.DisplayInfoImage;
import chw.intern.nts.reservation.dto.Product;
import chw.intern.nts.reservation.dto.ProductImage;
import chw.intern.nts.reservation.dto.ProductPrice;
import chw.intern.nts.reservation.service.CommentService;
import chw.intern.nts.reservation.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CommentService commentService;

	@GetMapping
	public Map<String, Object> productList(@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "start", defaultValue = "0") int start,
			@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {
		List<Product> ProductResponse = productService.getProductsByCategoryId(categoryId, start, limit);
		int totalCount = productService.getProductsCount(categoryId);
		Map<String, Object> map = new HashMap<>();
		map.put("items", ProductResponse);
		map.put("totalCount", totalCount);

		return map;
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> productDetailInfo(
			@PathVariable(name = "displayInfoId", required = true) Integer displayInfoId) {
		
		Map<String, Object> map = new HashMap<>();
		Integer productId = productService.getProductIdByDisplayInfoId(displayInfoId);
		
		List<Comment> commentList = commentService.getCommentsByDisplayInfoId(displayInfoId);
		// 네이밍 컨벤션 질문 (displayInfo, displayInfoResponse)
		DisplayInfo displayInfo = productService.getDisplayInfoById(displayInfoId);
		DisplayInfoImage displayInfoImage = productService.getDisplayInfoImageByDisplayInfoId(displayInfoId);
		List<ProductImage> productImageList = productService.getProductImagesByProductId(productId);
		List<ProductPrice> productPriceList = productService.getProductPricesByProductId(productId);
		
		//TODO 추후 서비스 레이어로 이동하면서 분리예정. 
		//stream이나 람다를 사용해 의도가 드러나면서도 좀 더 깔끔하게 할 수 없는지 공부
		double averageScore = 0;
		for (Comment comment : commentList) {
			averageScore += comment.getScore();
		}
		averageScore /= commentList.size();
		
		map.put("averageScore", averageScore);
		map.put("comments", commentList);
		map.put("displayInfo", displayInfo);
		map.put("displayInfoImage", displayInfoImage);
		map.put("productImages", productImageList);
		map.put("productPrices", productPriceList);
		
		return map;
	}
}
