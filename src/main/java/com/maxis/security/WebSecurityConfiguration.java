package com.maxis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/oauth/login").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/oauth/check_token").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/oauth/idToken-by-refreshToken").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/role").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers("/user").permitAll();

//		http.headers().defaultsDisabled().cacheControl();
//
//		http.csrf().disable().exceptionHandling()
//				.authenticationEntryPoint(
//						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//				.and().authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();

//		http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated().and().authorizeRequests()
//				.antMatchers("/oauth/login").permitAll().and().httpBasic();

	}

}
