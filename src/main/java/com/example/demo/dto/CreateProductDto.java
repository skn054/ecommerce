package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.Categorys;
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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDto {
	
	
	@NotBlank(message = "Product name cannot be blank")
	private String name;
	
	private String description;
	
	@NotNull(message = "Stock quantity cannot be null")
	@Min(value = 0, message = "Stock quantity cannot be negative")
	private Long stockQuantity;
	
	private String imageUrl;
	
	 @NotNull(message = "Price cannot be null")
	 @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
	private BigDecimal price;
	
	 @NotBlank(message = "Category cannot be blank")
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
