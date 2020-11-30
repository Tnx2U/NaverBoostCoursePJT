package chw.intern.nts.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

	// 컨트롤러가 실행되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String reservationEmail = (String) session.getAttribute("reservationEmail");

		LOGGER.info(handler.toString() + " is Called.");

		if (reservationEmail == null) {
			LOGGER.info("There is no login Info.");
		} else {
			LOGGER.info(String.format("User Login with %s.", reservationEmail));
			LOGGER.info(String.format("SessionCreationTime : %d,  RecentSessionCall : %d", session.getCreationTime(),
					session.getLastAccessedTime()));
			session.setMaxInactiveInterval(10 * 60);
		}

		return true;
	}
}
