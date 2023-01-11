package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//    	
//        UserDetails john = User.builder()
//            .username("john")
//            .password("{noop}test123")
//            .roles("EMPLOYEE")
//            .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("ADMIN")
//                .build();
//        
//        return new InMemoryUserDetailsManager(john, mary, susan);  
//    }
	
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		auth.inMemoryAuthentication()
//		.withUser("john").password("{noop}test123").roles("EMPLOYEE");
//		auth.inMemoryAuthentication()
//		.withUser("mary").password("{noop}test123").roles("EMPLOYEE","MANAGER");
//		auth.inMemoryAuthentication()
//		.withUser("susan").password("{noop}test123").roles("EMPLOYEE","ADMIN");
//		super.configure(auth);
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
		.and()
		.formLogin()
		.loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	http
//    			.authorizeRequests()
//    			.anyRequest()
//    			.authenticated()
//    			.and()
//    			.formLogin()
//    			.loginPage("/showMyLoginPage")		
//    			.loginProcessingUrl("/authenticateTheUser")
//    			.permitAll();
//    	return http.build();
//    }	
	
}
