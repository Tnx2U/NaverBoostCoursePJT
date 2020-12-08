package chw.intern.nts.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import chw.intern.nts.reservation.dao.CommentDao;
import chw.intern.nts.reservation.dao.FileInfoDao;
import chw.intern.nts.reservation.dao.ReservationUserCommentDao;
import chw.intern.nts.reservation.dto.Comment;
import chw.intern.nts.reservation.dto.CommentImage;
import chw.intern.nts.reservation.entity.FileInfo;
import chw.intern.nts.reservation.entity.ReservationUserComment;
import chw.intern.nts.reservation.entity.ReservationUserCommentImage;
import chw.intern.nts.reservation.service.CommentService;

@PropertySource("classpath:application.properties")
@Service
public class CommentServiceImpl implements CommentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
	private static final String EXTENSION_REGEXR = "^\\S+.(?i)(jpg|jpeg|png)$";
	
	@Value("${spring.filesrc.address}")
	private String fileSrcAddress;

	@Autowired
	CommentDao commentDao;

	@Autowired
	FileInfoDao fileInfoDao;

	@Autowired
	ReservationUserCommentDao reservationUserCommentDao;

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

	@Override
	@Transactional(readOnly = true)
	public Comment getCommentById(Integer commentId) {
		Comment comment = null;
		try {
			comment = commentDao.selectById(commentId);
			comment.setScore(convertDigit(comment.getScore()));

			List<CommentImage> commentImages = commentDao.selectAllByCommentId(commentId);
			comment.setCommentImages(commentImages);
		} catch (Exception e) {
			LOGGER.error("Error Occured with params : {commentId : {}} \r\n{}", commentId, e.getLocalizedMessage());
		}

		return comment;
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
	public Comment postComment(MultipartFile attachedImage, String comment, Integer productId, Integer score,
			Integer reservationInfoId) {
		Comment responseComment = null;
		long nowDateLong = new Date(System.currentTimeMillis()).getTime();

		try {
			ReservationUserComment reservationUserComment = new ReservationUserComment(productId, reservationInfoId,
					score, comment);
			Integer reservationUserCommentId = reservationUserCommentDao
					.insertReservationUserComment(reservationUserComment);

			if (attachedImage != null) {
				String fileName = String.format("%d_%d_%s", reservationInfoId, nowDateLong,
						attachedImage.getOriginalFilename());
				String saveFileName = String.format("img_comment/%d_%d_%s", reservationInfoId, nowDateLong,
						attachedImage.getOriginalFilename());
				String contentType = attachedImage.getContentType();

				FileInfo fileInfo = new FileInfo(fileName, saveFileName, contentType);
				Integer fileInfoId = fileInfoDao.insertFileInfo(fileInfo);

				ReservationUserCommentImage reservationUserCommentImage = new ReservationUserCommentImage(
						reservationInfoId, reservationUserCommentId, fileInfoId);
				Integer reservationUserCommentImageId = reservationUserCommentDao
						.insertReservationUserCommentImage(reservationUserCommentImage);

				// 외부 디렉토리에 파일 저장
				upLoadImage(attachedImage, saveFileName);
			}

			// 응답 데이터 생성
			responseComment = getCommentById(reservationUserCommentId);
		} catch (Exception e) {
			// Think : 코드상에서 가독성을 위해 메시지 줄바꿈을 하면 + 연산자 써야 하는데 성능이 떨어짐.
			LOGGER.error(
					"Error Occured with params : {attachedImageName : {}, comment : {}, productId : {}, score : {}, reservationInfoId : {}} \r\n{}",
					attachedImage.getOriginalFilename(), comment, productId, score, reservationInfoId,
					e.getLocalizedMessage());
		}

		return responseComment;
	}

	public void upLoadImage(MultipartFile attachedImage, String saveFileName) throws IOException {
		try(FileOutputStream outPutStream = new FileOutputStream(fileSrcAddress + saveFileName);
				InputStream inputStream = attachedImage.getInputStream();){
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = inputStream.read(buffer)) != -1) {
				outPutStream.write(buffer, 0, readCount);
			}
		}catch(IOException e){
			throw e;
		}
		
//		Matcher matcher = Pattern.compile(EXTENSION_REGEXR).matcher(attachedImage.getOriginalFilename());
//		if(matcher.find()) {
//			// 확장자 일치
//		}else {
//			// 일치 확장자 없음
//		}
//		ImageIO.write(im, formatName, output)
	}
}
