package com.manudev90.userapi.service;

import com.manudev90.userapi.exception.UserNotFoundException;
import com.manudev90.userapi.model.dto.UserRequestDTO;
import com.manudev90.userapi.model.dto.UserResponseDTO;

public interface UserService {
	
	public UserResponseDTO save(UserRequestDTO userRequestDTO);
	
	public UserResponseDTO findById(Long id) throws UserNotFoundException;
	
	public void update(Long id,UserRequestDTO userRequestDTO) throws UserNotFoundException;
	
	public void delete(Long id) throws UserNotFoundException;

}
