package com.nguyencthi.shopQuanAo.nguoiDung;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class nguoiDungConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	


	
	@Bean
	public UserDetailsService nguoidungDetailsService() {
		return new CustomNguoiDungDetailsService(); 
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(nguoidungDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			
			.authorizeRequests()
			.antMatchers("/admin").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/dang-nhap")
				.usernameParameter("emailLogin")
				.passwordParameter("passwordLogin")
				.failureForwardUrl("/login_fail_handler")
				.successForwardUrl("/login_success_handler")
			.and()
			.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
			.and()
			.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/")
			;
				
	}
	
	
	
	
}
