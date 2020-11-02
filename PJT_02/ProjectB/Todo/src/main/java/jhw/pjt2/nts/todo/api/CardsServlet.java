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
import jhw.pjt2.nts.todo.dto.Card;

@WebServlet("/cards")
public class CardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CardsServlet() {
        super();
    }
    
    //전체 카드목록을 반환
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		CardDao cardDao = new CardDao();
		List<Card> cardList = cardDao.getAllCard();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonedCardList = objectMapper.writeValueAsString(cardList);
		
		PrintWriter out = response.getWriter();
		out.println(jsonedCardList);
		out.close();
	}
}
