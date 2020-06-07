package com.dbs.backend;

import java.security.Principal;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableTransactionManagement
public class BackendApplication {
	
	private static Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		log.info("BackendApplication starts...");
		SpringApplication.run(BackendApplication.class, args);
	}
	
//    @Bean(initMethod = "migrate")
//    @DependsOn("springUtility")
//    public Flyway flyway() {
//        Flyway flyway = new Flyway();
//        flyway.setBaselineOnMigrate(true);
//        flyway.setDataSource(dataSource);
//        return flyway;
//    }
	
//	@RequestMapping("/user")
//	  public Principal user(Principal user) {
//	    return user;
//	  }
//	
//	  @Configuration
//	  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	      http
//	        .httpBasic()
//	      .and()
//	        .authorizeRequests()
//	          .antMatchers("/index.html", "/", "/home", "/login").permitAll()
//	          .anyRequest().authenticated();
//	    }
//	  }

}
