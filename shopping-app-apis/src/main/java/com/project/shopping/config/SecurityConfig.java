package com.project.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.shopping.Security.CustomUserDetailService;
import com.project.shopping.Security.JwtAuthenticationEntryPoint;
import com.project.shopping.Security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService userdetailservice;
	@Autowired
	private JwtAuthenticationEntryPoint jwtauthenticationpoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationfilter;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable()
	.authorizeHttpRequests()
	.antMatchers("/api/v1/auth/login").permitAll()
	.anyRequest()
	.authenticated()
	.and()
	.exceptionHandling().authenticationEntryPoint(this.jwtauthenticationpoint)
	.and()
	.sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	http.addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userdetailservice).passwordEncoder(passwordEncoder());
	}

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
    
 
    

	
}
