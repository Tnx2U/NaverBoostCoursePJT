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
		
		out.print(getCurrentTimeHtmlSrc());
		out.close();
	}


	private String getCurrentTimeHtmlSrc() {
		StringBuilder htmlText = new StringBuilder();
		String formatedTime = getFormatedCurrentTime();
		String centerPosStyle = "position: absolute;top: 50%;left: 50%;width: 450px;margin: -20px 0 0 -225px;";
		
		htmlText.append("<html>");
		htmlText.append("<head><title>today page</title></head>");
		htmlText.append("<body>");
		htmlText.append("<a href='http://localhost:8080/aboutme/index.html'>메인화면</a>");
		htmlText.append("<div style='"+ centerPosStyle +"'><h1>현재시간 : "+ formatedTime +"</h1></div>");
		htmlText.append("</html>");
		
		return htmlText.toString();
	}
	
	private String getFormatedCurrentTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");

		return currentDateTime.format(timeFormat);
	}
}
