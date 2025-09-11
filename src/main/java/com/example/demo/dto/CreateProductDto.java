package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.Categorys;
import com.example.demo.models.Product;

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
	
	
	

	  public static Product mapToProduct(CreateProductDto product,Categorys categorys) {
	         return Product.builder()
	                 .name(product.getName())
	                 .description(product.getDescription())
	                 .price(product.getPrice())
	                 .stockQuantity(product.getStockQuantity())
	                 .category(categorys) 
	                 .build();
	     }

}
