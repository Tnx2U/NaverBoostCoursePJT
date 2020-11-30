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
	// 컨트롤러가 실행된 후
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println(handler.toString() + " 가 종료되었습니다.  " + modelAndView.getViewName() + "을 view로 사용합니다.");
//	}

	// 컨트롤러가 실행되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String reservationEmail = (String) session.getAttribute("reservationEmail");

		LOGGER.info(handler.toString() + " 를 호출했습니다.");

		if (reservationEmail == null) {
			LOGGER.info("로그인되어 있지 않습니다.");
		} else {
			LOGGER.info(String.format("이메일 %s로 로그인되어 있습니다.", reservationEmail));
		}

		return true;
	}
}
