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
public class AddCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1. body로부터 input값들 가져옴
		//2. cardDto로부터 카드 객체로 만듬
		Card inputCard = new Card(request.getParameter("title"), request.getParameter("manager"), Integer.parseInt(request.getParameter("priority")));
		
		//3. cardDao의 addCard를 호출하며 파라미터로 줌
		CardDao cardDao = new CardDao();
		int insertCount = cardDao.addCard(inputCard);
		int insertedCardIndex = cardDao.getRecentCardId();
		
		CardOrderDao cardOrderDao = new CardOrderDao();
		//1번 컬럼 카드숫자 가져오기
		int columnSize = cardOrderDao.getColumnSizeById(1);
		
		CardOrder inputCardOrder = new CardOrder(1, insertedCardIndex, columnSize+1);
		cardOrderDao.addCardOrder(inputCardOrder);
		
		//4. main페이지로 리다이렉트
		response.sendRedirect("main");
	}
}
