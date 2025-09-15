package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productName;
    private Long quantity;
    private BigDecimal price;
    
    public static OrderItemDto mapToOrderItemDto(OrderItem item) {
    	
    	return OrderItemDto.builder().
    			productId(item.getProduct().getId())
    			.productName(item.getProduct().getName())
    			.quantity(item.getQuantity())
    			.price(item.getPrice())
    			.build();
    }
}
