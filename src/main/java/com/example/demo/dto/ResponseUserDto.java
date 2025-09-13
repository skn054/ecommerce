package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

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
	private List<AddressDto> addresses;
	
	
	public static ResponseUserDto mapToResponseUserDto(User savedUser) {
		
		
		 List<AddressDto> addressDtos = savedUser.getAddresses().stream()
			        .map(addr -> AddressDto.builder()
			            .street(addr.getStreet())
			            .city(addr.getCity())
			            .country(addr.getCountry())
			            .state(addr.getState())
			            .zipcode(addr.getZipcode())
			            .build())
			        .collect(Collectors.toList());
		 
		return ResponseUserDto
				.builder()
				.email(savedUser.getEmail())
				.id(savedUser.getId())
				.firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName())
				.addresses(addressDtos)
				.build();
		
		
				
		
		
	}

}
