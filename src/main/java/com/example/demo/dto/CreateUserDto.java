package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.models.Address;

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
public class CreateUserDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private List<AddressDto> addressDto;

}
