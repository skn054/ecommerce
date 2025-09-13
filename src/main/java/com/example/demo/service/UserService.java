package com.example.demo.service;
import com.example.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.ResponseUserDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.Address;
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
				.pasword(userDto.getPassword())
				.build();
		
		
		List<Address> addresses = mapAddressDtoToAdress(userDto, user);
		
		user.setAddresses(addresses);
		User savedUser = userRepository.save(user);
		return ResponseUserDto.mapToResponseUserDto(savedUser);
	}




	private List<Address> mapAddressDtoToAdress(CreateUserDto userDto, User user) {
		return userDto.getAddressDto()
								.stream()
								.map(addressDto ->{
									
									Address address = Address
											.builder()
											.city(addressDto.getCity())
											.country(addressDto.getCountry())
											.state(addressDto.getState())
											.street(addressDto.getStreet())
											.zipcode(addressDto.getZipcode())
											.user(user)
											.build();
									return address;
								}).collect(Collectors.toList());
	}
	
	
	public List<ResponseUserDto> getAllUsers() {
		return userRepository.findAll().stream().map(ResponseUserDto::mapToResponseUserDto).collect(Collectors.toList());
	}

	
	public ResponseUserDto getUserById(Long id) {
		User user =  getUserByInternalId(id);
		return ResponseUserDto.mapToResponseUserDto(user);
	}




	private User getUserByInternalId(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found By "+id));
	}

	
	public ResponseUserDto updateUser(CreateUserDto userDto,Long id) {
		
	
		User user = getUserByInternalId(id);
		List<Address> address = mapAddressDtoToAdress(userDto, user);
		
		user.setAddresses(address);
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhone(userDto.getPhone());
		
		User updatedUser = userRepository.save(user);
		return ResponseUserDto.mapToResponseUserDto(updatedUser);
		
	}


	




	


}
