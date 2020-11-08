package chw.intern.nts.reservation;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chw.intern.nts.reservation.config.ApplicationConfig;
import chw.intern.nts.reservation.dao.CategoryDao;
import chw.intern.nts.reservation.dto.Category;

public class SelectAllTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		CategoryDao categoryDao = ac.getBean(CategoryDao.class);
		
		List<Category> list = categoryDao.selectAll();
		
		for(Category ct : list) {
			System.out.println(ct.toString());
		}
		
		System.out.println(categoryDao.selectById(2).toString());
	} 
}
