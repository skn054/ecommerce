package com.example.demo.service;
import com.example.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ResponseUserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.User;

@Service
public class UserService {

    private final UserRepository userRepository;


    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	
	
	
	public ResponseUserDto createUser(CreateUserDto userDto) {
		
		User user = User
				.builder()
				.email(userDto.getEmail())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.phone(userDto.getPhone())
				.addresses(Arrays.asList(userDto.getAddress()))
				.build();
		
		User savedUser = userRepository.save(user);
		return ResponseUserDto.mapToResponseUserDto(savedUser);
	}
	
	
	public List<ResponseUserDto> getAllUsers() {
		return userRepository.findAll().stream().map(ResponseUserDto::mapToResponseUserDto).collect(Collectors.toList());
	}

	
	public ResponseUserDto getUserById(Long id) {
		User user =  getUserByInternalId(id);
		return ResponseUserDto.mapToResponseUserDto(user);
	}




	private User getUserByInternalId(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found By "+id));
	}

	
	public ResponseUserDto updateUser(CreateUserDto userDto,Long id) {
		User user = getUserByInternalId(id);
		user.setAddresses(Arrays.asList(userDto.getAddress()));
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhone(userDto.getPhone());
		
		User updatedUser = userRepository.save(user);
		return ResponseUserDto.mapToResponseUserDto(updatedUser);
		
	}


	




	


}
