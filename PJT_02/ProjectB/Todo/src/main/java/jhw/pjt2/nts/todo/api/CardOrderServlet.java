package jhw.pjt2.nts.todo.api;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jhw.pjt2.nts.todo.dao.CardOrderDao;

@WebServlet("/card-order")
public class CardOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CardOrderServlet() {
		super();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String input = getBody(request);
		JSONParser jsonParser = new JSONParser();
		JSONObject cardInfo = null;
		
		try {
			cardInfo = (JSONObject) jsonParser.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int columnId = Integer.parseInt(cardInfo.get("columnId").toString());
		int cardId = Integer.parseInt(cardInfo.get("cardId").toString());
		int cardOrder = Integer.parseInt(cardInfo.get("cardOrder").toString());
		
		//column+1의 길이 구함
		CardOrderDao cardOrderDao = new CardOrderDao();
		int dstColumnSize = cardOrderDao.getColumnSizeById(columnId+1);
		//해당 카드ID 의 COLUMN+1, 길이로 변환
		cardOrderDao.updateClickedCardOrder(cardId, columnId+1, dstColumnSize+1);
		//기존에 있던 컬럼에 ORDER > 애들 1씩 감소
		cardOrderDao.reduceCardOrder(columnId, cardOrder);
		
		response.setStatus(200);
	}

	// request의 BODY를 스트림하여 String값으로 리턴
	private String getBody(HttpServletRequest request) {
		int streamSize = 128;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = request.getReader();
			if (bufferedReader != null) {
				char[] charBuffer = new char[streamSize];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		return stringBuilder.toString();
	}
}
