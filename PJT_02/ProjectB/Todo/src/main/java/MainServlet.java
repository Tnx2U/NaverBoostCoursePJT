import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jhw.pjt2.nts.todo.dto.Card;

@WebServlet({ "/main", "/" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		//전체 데이터 셋 가져오기
		// 데이터 전처리 함수화
		List<Card> orderedCards[] = new ArrayList[3];
		orderedCards = getOrderedCards();
		request.setAttribute("orderedCards", orderedCards);
		
		//디스패쳐로 jsp로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

	private List<Card>[] getOrderedCards() {
		//allCardOrders 정보 가져옴
		//Card정보 가져옴
		
		return null;
	}

}
