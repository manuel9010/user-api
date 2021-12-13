package com.manudev90.userapi.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manudev90.userapi.model.dto.UserRequestDTO;
import com.manudev90.userapi.model.dto.UserResponseDTO;
import com.manudev90.userapi.service.UserService;

@RestController
@Component
public class UserController {

	@Autowired
	UserService userService;


	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    	UserResponseDTO userResponseDTO = userService.save(userRequestDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") long id) {
    	UserResponseDTO userResponseDTO = userService.findById(id);
        return ResponseEntity.ok().body(userResponseDTO);
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<HttpStatus>  update(@PathVariable("id") long id,@RequestBody UserRequestDTO userRequestDTO) {
    	 userService.update(id,userRequestDTO);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus>  delete(@PathVariable("id") long id) {
    	 userService.delete(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
