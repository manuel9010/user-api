package com.manudev90.userapi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.manudev90.userapi.exception.UserNotFoundException;
import com.manudev90.userapi.model.entity.UserEntity;
import com.manudev90.userapi.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username)  {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(username);
		Example<UserEntity> userExample = Example.of(userEntity);
		Optional<UserEntity> userOptional = userRepository.findOne(userExample);
		if (userOptional.isPresent()) {
			userEntity = userOptional.get();
			return new User(userEntity.getEmail(), userEntity.getPassword(),
					new ArrayList<>());
			}else {
			throw new UserNotFoundException("Usario no encontrado con el email: " + username);
		}
	}
}