package com.example.demo.dto;

import com.example.demo.models.Address;

import lombok.*;

@Getter
@Setter
@Builder
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    
    
    public static AddressDto getAddressDto(Address address) {
    	return AddressDto.builder()
    			.city(address.getCity())
    			.country(address.getCountry())
    			.state(address.getState())
    			.zipcode(address.getZipcode())
    			.street(address.getStreet())
    			.build();
    }
}