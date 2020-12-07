package chw.intern.nts.reservation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import chw.intern.nts.reservation.service.ImageService;

@PropertySource("classpath:application.properties")
@Service
public class ImageServiceImpl implements ImageService {
	@Value("${spring.filesrc.address}")
	private String fileSrcAddress;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Override
	public byte[] getImageByFileSaveName(String saveFileName) {
		FileInputStream imageStream = null;
		byte[] imageByte = null;

		try {
			imageStream = new FileInputStream(
					new File(fileSrcAddress + saveFileName));
			imageByte = IOUtils.toByteArray(imageStream);
		} catch (IOException e) {
			LOGGER.error("Error Occured with params : {saveFileName : {}} \r\n{}", saveFileName,
					e.getLocalizedMessage());
		}

		return imageByte;
	}
}
