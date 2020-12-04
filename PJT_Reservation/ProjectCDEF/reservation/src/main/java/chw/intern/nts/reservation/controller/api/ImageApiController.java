package chw.intern.nts.reservation.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chw.intern.nts.reservation.service.ImageService;



@RestController
@RequestMapping(path = "/api/image")
public class ImageApiController {
	@Autowired
	ImageService imageService;

	@GetMapping
	public byte[] getImage(@RequestParam(name = "saveFileName", required = true) String saveFileName) {
		return imageService.getImageByFileSaveName(saveFileName);
	}
}
