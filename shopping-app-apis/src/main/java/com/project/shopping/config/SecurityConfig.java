package com.project.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.project.shopping.Security.CustomUserDetailService;
import com.project.shopping.Security.JwtAuthenticationEntryPoint;
import com.project.shopping.Security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String[] PUBLIC_URLS = {
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};
	
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
	.antMatchers(PUBLIC_URLS).permitAll()
	.antMatchers(HttpMethod.GET).permitAll()
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
