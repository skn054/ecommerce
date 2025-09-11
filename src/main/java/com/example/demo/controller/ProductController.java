package com.example.demo.controller;
import com.example.demo.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ResponseProductDto;
import com.example.demo.models.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }
	
	@PostMapping
	public ResponseEntity<ResponseProductDto> addProdcuct(@RequestBody CreateProductDto product) {
			
		ResponseProductDto productDto = productService.createProduct(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
		
	}
	
	
	


}
