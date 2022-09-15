package com.project.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopping.Security.JwtTokenHelper;
import com.project.shopping.payloads.JwtAuthRequest;
import com.project.shopping.payloads.JwtAuthResponse;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/auth/")
@Slf4j
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwttokenhelper;
	
	@Autowired
	private UserDetailsService userdetailservice;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request
			) throws Exception
	{	log.info("inside contoller");
		authenticate(request.getUsername(),request.getPassword());
		UserDetails userdetails = userdetailservice.loadUserByUsername(request.getUsername());
		System.out.println(userdetails.getUsername());
		String generatedToken = jwttokenhelper.generateToken(userdetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(generatedToken);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		System.out.println("authenticationToken - " +authenticationToken.getName());
		try {
		authenticationmanager.authenticate(authenticationToken);}
		catch(BadCredentialsException b)
		{
			System.out.println("Invalid Details");
			throw new Exception("Invalid username or password");
		}
	}
}
