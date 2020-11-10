package chw.intern.nts.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "chw.intern.nts.reservation.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// css, img, js 를 제외한 요청에 대한 모든 처리만 dispatcherServlet이 실행하도록 설정
		// 경로에 css, img, js가 붙은요청들은 각각 적절한 디렉토리에서 찾도록 경로설정
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/static/css/**").addResourceLocations("/static/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/static/img/**").addResourceLocations("/static/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/").setCachePeriod(31556926);
	}

	// default servlet handler를 사용하게 합니다.
	// url이 지정되지 않은 요청에 대해서 자동 처리하도록 설정?
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// 루트경로로 요청이 오면 뷰를 할당하기 위해 main으로 요청이름을 설정
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		System.out.println("addViewControllers가 호출됩니다. ");
		registry.addViewController("/").setViewName("main");
	}

	// 뷰 요청에 대해 요청받은 이름의 앞뒤로 prefix, suffix을 할당하여 jsp 파일을 찾도록 만들어줌
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
