package com.example.demo.controller;
import com.example.demo.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ResponseUserDto;
import com.example.demo.models.User;

@RestController("/api/users")
public class UserController {

    private final UserService userService;


    UserController(UserService userService) {
        this.userService = userService;
    }
	
	
    @PostMapping
	public ResponseEntity<ResponseUserDto> createUser(@RequestBody CreateUserDto createUserDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDto));
	}
    
    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> getAllUsers() {
    	
    	return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id) {
    	
    	return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@RequestBody CreateUserDto userDto, @PathVariable Long id) {
    		
    		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, id));
    	
    }

}
