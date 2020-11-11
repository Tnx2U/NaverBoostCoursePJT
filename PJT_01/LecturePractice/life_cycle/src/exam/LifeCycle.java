package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycle() {
        super();
    }

//	public void init(ServletConfig config) throws ServletException {
//		
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>form</title></head>");
		out.print("<body>");
		out.print("<h1>lifeCycle and header page</h1>");
		
		// header 정보 출력
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			out.print(headerName + " : " + headerValue + "<br>");
		}
		
		// 파라미터 정보 출력
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		out.print("<h2>"+ name +"</h2>");
		out.print("<h2>"+ age +"</h2>");
		
		
		// 기타 정보 출력
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		String contentPath = request.getContextPath();
		String remoteAddr = request.getRemoteAddr();
		
		
		out.println("uri : " + uri + "<br>");
		out.println("url : " + url + "<br>");
		out.println("contentPath : " + contentPath + "<br>");
		//클라이언트 주솟값
		out.println("remoteAddr : " + remoteAddr + "<br>");
		
		out.print("</body>");
		out.print("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

//	public void destroy() {
//		
//	}
//
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

}
