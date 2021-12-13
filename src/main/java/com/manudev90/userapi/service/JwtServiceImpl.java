package com.manudev90.userapi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.manudev90.userapi.util.JwtTokenUtil;

@Service
public class JwtServiceImpl implements JwtService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	
   public String  generateToken(String username, String password) {
	   
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		 UserDetails userDetails = new User(username, password,
				new ArrayList<>());

		 String token = jwtTokenUtil.generateToken(userDetails);
		 
		 return token;
   }

}
