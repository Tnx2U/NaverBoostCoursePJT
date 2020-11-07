package chw.intern.nts.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//이 클래스가 java application Config파일임을 지정
@Configuration
@Import({DBConfig.class})
// 사용자가 일일이 지정하지 않아도 자동으로 지정된 패키지 내부에서 bean으로 만들어줘야 할 것들을 자동으로 등록해준다.
//@ComponentScan("chw.intern.nts.reservation")
public class ApplicationConfig {
//	@Bean
//	public Category category() {
//		Category category = new Category();
//		return category;
//	}
	
	
}
