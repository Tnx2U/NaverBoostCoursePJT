package chw.intern.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.dto.Promotion;
import chw.intern.nts.reservation.service.PromotionService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
	@Autowired
	PromotionService promotionService;

	@GetMapping
	public Map<String, Object> getPromotions() {
		List<Promotion> promotionResponse = promotionService.getAllPromotionWithImgUrl();
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("items", promotionResponse);

		return responseMap;
	}
}
