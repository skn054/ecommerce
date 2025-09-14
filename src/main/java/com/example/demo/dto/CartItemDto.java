package com.example.demo.dto;

import com.example.demo.models.CartItem;

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
public class CartItemDto {
	
	private Long productId;
	private Long quantity;
	
	public static CartItemDto mapToCartItemDto(CartItem cartItem) {
		
		CartItemDto cartItemDto = CartItemDto.builder().productId(cartItem.getProduct().getId()).quantity(cartItem.getQuantity()).build();
		return cartItemDto;
		
	}

}
