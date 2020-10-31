import java.io.IOException;
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

@WebServlet({ "/main", "/" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 3;

	public MainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		List<Card> orderedCards[] = new ArrayList[3];
		orderedCards = getOrderedCards();
		List<Column> columnList = new ArrayList<>();
		ColumnDao columnDao = new ColumnDao();
		columnList = columnDao.getAllColumn();
		
		request.setAttribute("orderedCards", orderedCards);
		request.setAttribute("columnList", columnList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

	// 컬럼 안에서 카드 순서를 고려한 카드정보를 리턴
	private List<Card>[] getOrderedCards() {
		List<Card> orderedCards[] = new ArrayList[COLUMN_LENGTH];
		int orderedCardsIdx = 0;

		CardDao cardDao = new CardDao();
		List<Card> cardList = cardDao.getOrderedAllCard();
		List<Card> tempList = new ArrayList<>();
		int beforeColumn = -1;

		for (int i = 0; i < orderedCards.length; i++) {
			orderedCards[i] = new ArrayList<>();
		}

		if (cardList.size() != 0) {
			beforeColumn = cardList.get(0).getColumnId();
		}
		
		// orderedCards의 각 행에
		for (int i = 0; i < cardList.size(); i++) {
			if (cardList.get(i).getColumnId() != beforeColumn) {
				orderedCardsIdx = cardList.get(i).getColumnId()-1;
			}

			orderedCards[orderedCardsIdx].add(cardList.get(i).clone());
		}

		return orderedCards;
	}

}
