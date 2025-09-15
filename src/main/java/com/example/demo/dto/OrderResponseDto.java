package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	
	private String status;
	
	 private AddressDto shippingAddress;
	
	private BigDecimal totalAmount;
	
	private List<OrderItemDto> orderItemList = new ArrayList<>();
	
	public static OrderResponseDto mapToOrderDto(Order order) {
		
		OrderResponseDto responseDto = OrderResponseDto.builder()
				.orderId(order.getId())
				.city(order.getShippingAddress().getCity())
				.userId(order.getUser().getId())
				.totalAmount(order.getTotalAmount())
				.status(order.getStatus().name())
				.build();
		
		order.getOrderItems().forEach(item ->{
			
			OrderItemDto dtoItem = OrderItemDto.mapToOrderItemDto(item);
			responseDto.orderItemList.add(dtoItem);
		});
		
		AddressDto address = AddressDto.getAddressDto(order.getShippingAddress());
		responseDto.setShippingAddress(address);
		return responseDto;
				
	}
}
