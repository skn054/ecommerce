package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.Order;

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
public class OrderResponseDto {
	
	private Long orderId;
	
	private String city;
	
	private Long userId;
	
	private BigDecimal totalAmount;
	
	public static OrderResponseDto mapToOrderDto(Order order) {
		
		OrderResponseDto responseDto = OrderResponseDto.builder()
				.orderId(order.getId())
				.city(order.getShippingAddress().getCity())
				.userId(order.getUser().getId())
				.totalAmount(order.getTotalAmount())
				.build();
		return responseDto;
				
	}
}
