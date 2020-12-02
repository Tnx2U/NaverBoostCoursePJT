package chw.intern.nts.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import chw.intern.nts.reservation.dao.CommentDao;
import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.CommentImage;
import chw.intern.nts.reservation.service.CommentService;

@PropertySource("classpath:application.properties")
@Service
public class CommentServiceImpl implements CommentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Value("${spring.filesrc.address}")
	private String fileSrcAddress;
	
	@Autowired
	CommentDao commentDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByDisplayInfoId(Integer displayInfoId) {
		List<Comment> commentList = Collections.emptyList();
		try {
			commentList = commentDao.selectAllByDisplayInfoId(displayInfoId);
			for (Comment comment : commentList) {
				comment.setScore(convertDigit(comment.getScore()));
				Integer commentId = comment.getCommentId();
				List<CommentImage> commentImages = commentDao.selectAllByCommentId(commentId);
				comment.setCommentImages(commentImages);
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {displayInfoId : {}} \r\n{}", displayInfoId,
					e.getLocalizedMessage());
		}

		return commentList;
	}

	private double convertDigit(double number) {
		double convertedNumber = number;

		convertedNumber = Math.round(convertedNumber * 10.0) / 10;

		return convertedNumber;
	}

	@Override
	public double getAverageScore(List<Comment> commentList) {
		double averageScore = 0;
		// TODO stream이나 람다를 사용해 의도가 드러나면서도 좀 더 깔끔하게 할 수 없는지 공부
		for (Comment comment : commentList) {
			averageScore += comment.getScore();
		}
		if (averageScore != 0) {
			averageScore /= commentList.size();
		}

		averageScore = Math.round(averageScore * 10) / 10.0;

		return averageScore;
	}

	@Override
	@Transactional(readOnly = false)
	public Comment postComment(MultipartFile file) {
		Comment comment = null;

		//이미지 파일 저장
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());

		try (FileOutputStream fos = new FileOutputStream(fileSrcAddress + file.getOriginalFilename());
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}

		//코멘트 dao를 통해 insert
		
		
		return comment;
	}
}
