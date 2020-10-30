package jhw.pjt2.nts.todo.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import jhw.pjt2.nts.todo.dao.CardDao;
import jhw.pjt2.nts.todo.dto.Card;

/**
 * Servlet implementation class CardByIdServlet
 */
@WebServlet("/cards/*")
public class CardByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CardByIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String idStr = pathParts[1];
		int id = Integer.parseInt(idStr);
		
		CardDao cardDao = new CardDao();
		Card targetCard = cardDao.getCardById(id);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonedTargetCard = objectMapper.writeValueAsString(targetCard);
		PrintWriter out = response.getWriter();
		out.print(jsonedTargetCard);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
