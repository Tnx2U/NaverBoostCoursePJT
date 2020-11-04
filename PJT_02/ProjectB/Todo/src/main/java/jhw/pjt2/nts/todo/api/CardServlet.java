package jhw.pjt2.nts.todo.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
		
		Card inputCard = new Card(request.getParameter("title"), request.getParameter("manager"), Integer.parseInt(request.getParameter("priority")));

		try {
			int insertCount = cardDao.addCard(inputCard);
			int insertedCardIndex = cardDao.getRecentCardId();
			int columnSize = cardOrderDao.getColumnSizeById(TODO_COLUMN_ID);
			
			CardOrder inputCardOrder = new CardOrder(1, insertedCardIndex, columnSize+1);
			cardOrderDao.addCardOrder(inputCardOrder);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("main");
	}
}
