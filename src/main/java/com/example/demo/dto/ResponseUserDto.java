package com.example.demo.dto;

import java.util.List;

import com.example.demo.models.Address;
import com.example.demo.models.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserDto {
	
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private List<Address> address;
	
	
	public static ResponseUserDto mapToResponseUserDto(User savedUser) {
		return ResponseUserDto
				.builder()
				.address(savedUser.getAddresses())
				.email(savedUser.getEmail())
				.id(savedUser.getId())
				.firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName())
				.build();
		
		
				
		
		
	}

}
