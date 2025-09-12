package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.Product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDto{
	
	
	private Long id;
	
	private String name;
	
	private String description;
	
	
	private Long stockQuantity;
	
	
	private BigDecimal price;
	
	
	private String categoryName;
	
	
	
	  public static ResponseProductDto mapToResponseDto(Product product) {
	         return ResponseProductDto.builder()
	                 .id(product.getId()) 
	                 .name(product.getName())
	                 .description(product.getDescription())
	                 .price(product.getPrice())
	                 .stockQuantity(product.getStockQuantity())
	                 .categoryName(product.getCategory().getName()) 
	                 .build();
	     }
	
	

}
