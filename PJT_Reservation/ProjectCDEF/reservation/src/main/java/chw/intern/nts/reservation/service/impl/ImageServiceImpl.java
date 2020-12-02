package chw.intern.nts.reservation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import chw.intern.nts.reservation.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Override
	public byte[] getImageByFileSaveName(String saveFileName) {
		FileInputStream imageStream = null;
		byte[] imageByte = null;

		try {
			imageStream = new FileInputStream(
					new File("C:\\Project\\cho_hyun_wook\\PJT_Reservation\\ExternalSrc\\" + saveFileName));
			imageByte = IOUtils.toByteArray(imageStream);
		} catch (IOException e) {
			LOGGER.error("Error Occured with params : {saveFileName : {}} \r\n{}", saveFileName,
					e.getLocalizedMessage());
		}

		return imageByte;
	}
}
