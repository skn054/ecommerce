package com.example.demo.dto;

import java.math.BigDecimal;

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
public class CreateProductDto {
	
	private String name;
	
	private String description;
	
	private Long stockQuantity;
	
	private String imageUrl;
	
	private BigDecimal price;
	
	private String category;

}
