package com.manudev90.userapi.service;

public interface JwtService {
	
	public String  generateToken(String username, String password);

}
