package jhw.pjt2.nts.todo.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jhw.pjt2.nts.todo.dao.ColumnDao;
import jhw.pjt2.nts.todo.dto.Card;
import jhw.pjt2.nts.todo.dto.Column;

@WebServlet(asyncSupported = true, urlPatterns = { "/addpage" })
public class AddPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		RequestDispatcher dispatcher = request.getRequestDispatcher("add_todo.jsp");
		dispatcher.forward(request, response);
	}
}
