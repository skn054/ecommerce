package com.example.demo.controller;
import com.example.demo.service.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartItemDto;
import com.example.demo.dto.CartResponseDto;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;


    CartController(CartService cartService) {
        this.cartService = cartService;
    }
	
	
	
	@PostMapping
	public ResponseEntity<Void> addProductToCart(@RequestHeader("X-User-ID") Long userId,@RequestBody CartItemDto cartItemDto) {
		
				cartService.addProductToCart(userId, cartItemDto);
				
				return ResponseEntity.ok().build();
			
		
	}
	
	@GetMapping
	public ResponseEntity<CartResponseDto> getCart(@RequestHeader("X-User-ID") Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart(userId));
	}
	
	public ResponseEntity<?> deleteCart(@RequestHeader("X-User-ID") Long userId){
		
		cartService.deleteCart(userId);
		return ResponseEntity.noContent().build();
	}

}
