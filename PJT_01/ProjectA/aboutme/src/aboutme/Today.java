package aboutme;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/today")
public class Today extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Today() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(getHtmlSrc());
		out.close();
	}


	private String getHtmlSrc() {
		StringBuilder htmlText = new StringBuilder();
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
		String formatedTime = currentDateTime.format(timeFormat);
		
		htmlText.append("<html>");
		htmlText.append("<head><title>today page</title></head>");
		htmlText.append("<body>");
		htmlText.append("<a href='http://localhost:8080/aboutme/index.html'>메인화면</a>");
		htmlText.append("<h1 style='margin: 50px;'>현재시간 : "+ formatedTime +"</h1>");
		htmlText.append("</html>");
		
		return htmlText.toString();
	}
}
