package com.example.demo.service;

import java.util.Locale.Category;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.models.Categorys;
import com.example.demo.models.Product;


@Service
public class ProductService {

    private final ProductRepository productRepository;


    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    
	
	
	
     public Product createProduct(CreateProductDto productdto) {
    	 
    	 
    	 Categorys category = new Categorys();
    	 category.setName(productdto.getName());
    	 
    	 Product product = Product
    			 .builder()
    			 .description(productdto.getDescription())
    			 .name(productdto.getName())
    			 .imageUrl(productdto.getImageUrl())
    			 .stockQuantity(productdto.getStockQuantity())
    			 .price(productdto.getPrice())
    			 .category(category)
    			 .build();
    	 
    	 return productRepository.save(product);
    	 
     }

}
