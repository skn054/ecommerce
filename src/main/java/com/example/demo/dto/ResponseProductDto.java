package com.example.demo.dto;

import java.math.BigDecimal;

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
	
	@NotBlank(message = "Product name cannot be blank")
	private Long id;
	private String name;
	
	private String description;
	
	@NotNull(message = "Stock quantity cannot be null")
	@Min(value = 0, message = "Stock quantity cannot be negative")
	private Long stockQuantity;
	
	 @NotNull(message = "Price cannot be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
	private BigDecimal price;
	
	@NotBlank(message = "Category cannot be blank")
	private String categoryName;
	
	

}
