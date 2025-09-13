package com.example.demo.dto;

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
}