package chw.intern.nts.reservation.argumentresolver;

import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HeaderMapArgumentResolver implements HandlerMethodArgumentResolver {

	// 컨트롤러 메서드 인자가 4개인 경우 매번호출
	// 각 파라미터에 원하는 정보가 있는지 판단해 boolean값 리턴
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType() == HeaderInfo.class;
	}

	// supportsParameter가 true를 리턴하면 호출
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HeaderInfo headerInfo = new HeaderInfo();

		Iterator<String> headerNames = webRequest.getHeaderNames();
		while (headerNames.hasNext()) {
			String headerName = headerNames.next();
			String headerValue = webRequest.getHeader(headerName);
			System.out.println(headerName + " , " + headerValue);
			headerInfo.put(headerName, headerValue);
		}

		return headerInfo;
	}

}
