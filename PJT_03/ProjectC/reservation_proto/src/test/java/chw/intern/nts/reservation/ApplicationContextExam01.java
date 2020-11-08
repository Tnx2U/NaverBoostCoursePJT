package chw.intern.nts.reservation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import chw.intern.nts.reservation.dto.Category;

public class ApplicationContextExam01 {
	public static void main(String[] args) {
		// resource에 만든 xml을 읽어서 applicationConext객체를 만들어라~
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		Category category = (Category) ac.getBean("category");
	}
}
