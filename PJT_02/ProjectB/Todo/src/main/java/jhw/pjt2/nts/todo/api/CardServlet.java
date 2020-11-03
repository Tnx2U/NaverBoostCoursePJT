package jhw.pjt2.nts.todo.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jhw.pjt2.nts.todo.dao.CardDao;
import jhw.pjt2.nts.todo.dao.CardOrderDao;
import jhw.pjt2.nts.todo.dto.Card;
import jhw.pjt2.nts.todo.dto.CardOrder;

@WebServlet("/card")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TODO_COLUMN_ID = 1;
       
    public CardServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CardDao cardDao = new CardDao();
		CardOrderDao cardOrderDao = new CardOrderDao();
		
		//1. body로부터 input값들 가져옴
		//2. cardDto로부터 카드 객체로 만듬
		//3. cardDao의 addCard를 호출하며 파라미터로 줌
		Card inputCard = new Card(request.getParameter("title"), request.getParameter("manager"), Integer.parseInt(request.getParameter("priority")));

		//4. card입력이 정상적인지 확인하기 위한 insertCount와 해당 카드의 인덱스인 insertedCardIndex를 가져옴
		int insertCount = cardDao.addCard(inputCard);
		int insertedCardIndex = cardDao.getRecentCardId();
		
		// todo 컬럼이 보유한 카드갯수를 가져옴
		int columnSize = cardOrderDao.getColumnSizeById(TODO_COLUMN_ID);
		
		// 해당 사이즈 + 1 의 위치에 새로운 카드를 삽입
		CardOrder inputCardOrder = new CardOrder(1, insertedCardIndex, columnSize+1);
		cardOrderDao.addCardOrder(inputCardOrder);
		
		//4. main페이지로 리다이렉트
		response.sendRedirect("main");
	}
}
