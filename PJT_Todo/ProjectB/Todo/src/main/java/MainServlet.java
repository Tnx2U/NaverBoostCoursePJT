import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jhw.pjt2.nts.todo.dao.CardDao;
import jhw.pjt2.nts.todo.dao.CardOrderDao;
import jhw.pjt2.nts.todo.dao.ColumnDao;
import jhw.pjt2.nts.todo.dto.Card;
import jhw.pjt2.nts.todo.dto.CardOrder;
import jhw.pjt2.nts.todo.dto.Column;
import jhw.pjt2.nts.todo.global.JdbcDriverLoader;

@WebServlet({ "/main", "" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 3;

	public MainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		try {
			JdbcDriverLoader.getStaticJdbcDriver();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException occured in declare com.mysql.jdbc.Driver");
			System.out.println("Server down Because of jdbc driver");
			response.setStatus(400);
			return;
		}

		List<Card> orderedCards[] = new ArrayList[3];
		orderedCards = getOrderedCards();
		List<Column> columnList = new ArrayList<>();
		ColumnDao columnDao = new ColumnDao();
		columnList = columnDao.getAllColumn();

		if(columnList == null || orderedCards == null) {
			response.setStatus(400);
		}else {
			request.setAttribute("orderedCards", orderedCards);
			request.setAttribute("columnList", columnList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		}
	}

	// 컬럼 안에서 카드 순서를 고려한 카드정보를 리턴
	private List<Card>[] getOrderedCards() {
		List<Card> orderedCards[] = new ArrayList[COLUMN_LENGTH];
		int orderedCardsIdx = 0;

		CardDao cardDao = new CardDao();
		List<Card> cardList;
		try {
			cardList = cardDao.getOrderedAllCard();

			List<Card> tempList = new ArrayList<>();
			int beforeColumn = -1;
			
			for (int i = 0; i < orderedCards.length; i++) {
				orderedCards[i] = new ArrayList<>();
			}

			if (cardList.size() != 0) {
				beforeColumn = cardList.get(0).getColumnId();
			}
			
			// orderedCards의 각 행에 컬럼별 카드 삽입
			for (Card card : cardList) {
				if (card.getColumnId() != beforeColumn) {
					orderedCardsIdx = card.getColumnId() - 1;
					beforeColumn = card.getColumnId();
				}
				orderedCards[orderedCardsIdx].add(card.clone());
			}

		} catch (SQLException e) {
			System.out.println("SQLException occured in getOrderedCards(). cant load data");
			return null;
		}

		return orderedCards;
	}
}
