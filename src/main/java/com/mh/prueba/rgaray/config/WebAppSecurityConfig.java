package com.mh.prueba.rgaray.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuracion de Spring Web Security.
 *
 * @author rene.garay
 * @version $Revision$ $Date$
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired @Qualifier("authenticationProvider")
	AuthenticationProvider authenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/mh/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests()
//			.antMatchers("/mh/user/add").access("hasRole('ROLE_ADMIN')")
//			.antMatchers("/mh/user/list").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
			.antMatchers("/mh/auth/login","/mh/auth/logout").permitAll()
			.antMatchers("/mh/error/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
	    	.loginPage("/mh/auth/login")
	    	.defaultSuccessUrl("/mh/")
	    	.failureUrl("/mh/auth/login?error")
	    	.usernameParameter("username")
	    	.passwordParameter("password")
		.and()
		    .logout().logoutUrl("/mh/auth/logout")
		    .logoutSuccessUrl("/mh/auth/login")
		.and()
		   .csrf();

	}
}