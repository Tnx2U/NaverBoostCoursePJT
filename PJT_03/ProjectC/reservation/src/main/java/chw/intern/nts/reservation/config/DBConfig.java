package chw.intern.nts.reservation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DBConfig implements TransactionManagementConfigurer{
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;
	
	//데이터 커넥션풀을 관리하기 위한 bean객체
    @Bean
    public DataSource dataSource() {
    	//왜 DriverManagerDataSources는 프로퍼티가 먹히고 BasicDataSource은 안되는지 조사
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
        return dataSource;
    }
    
    //이 클래스가 상속한 트랜잭션처리할 함수 오버라이드
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
    	return transactionManager();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
    	return new DataSourceTransactionManager(dataSource());
    }
}
