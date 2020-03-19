package vn.easycredit.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


/**
 * @author HienNT
 *
 */

@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
@ComponentScan(value = "vn.easycredit")
@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories(value = "vn.easycredit")
public class EvnProjectApplication {
	
//	@Value("${spring.queries.roles-query}")
//	private String rolesQuery;
	
//	@Autowired
//	DataSource dataSource1;
	
	/*@Bean(name = "dataSourceTest")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource.setUrl("jdbc:postgresql://10.0.27.26:5432/oauth2provider");
		driverManagerDataSource.setUsername("oauth2provider");
		driverManagerDataSource.setPassword("Z(7(X=yb`rgT");
		return driverManagerDataSource;
	}*/
	
//	@Bean(name = "dataSourceTest")
//	public DriverManagerDataSource dataSource() {
//		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//		driverManagerDataSource.setUrl("jdbc:postgresql://10.0.27.26:5432/oauth2provider");
//		driverManagerDataSource.setUsername("oauth2provider");
//		driverManagerDataSource.setPassword("Z(7(X=yb`rgT");
//		return driverManagerDataSource;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(EvnProjectApplication.class, args);
	}
}
