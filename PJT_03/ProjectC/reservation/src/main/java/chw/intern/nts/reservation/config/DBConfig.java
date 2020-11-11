package chw.intern.nts.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer{
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://10.113.116.52:13306/user05";
	private String username = "user05";
	private String password = "user05";
	
	//데이터 커넥션풀을 관리하기 위한 bean객체
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
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
