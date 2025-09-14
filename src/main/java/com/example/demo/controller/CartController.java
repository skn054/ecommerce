package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartItemDto;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	
	public void addProductToCart(@RequestHeader Long UserId,@RequestBody CartItemDto cartItemDto) {
		
			
			
		
	}

}
