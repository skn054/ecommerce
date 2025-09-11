package com.example.demo.service;

import java.util.Locale.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ResponseProductDto;
import com.example.demo.models.Categorys;
import com.example.demo.models.Product;


@Service
public class ProductService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;


    ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    
	
	
	
     public ResponseProductDto createProduct(CreateProductDto productdto) {
    	 	
    	 Categorys category =  categoryRepository
    			 .findByNameIgnoreCase(productdto.getCategory())
    			 .orElseGet(()->{
    				 Categorys newCategory = new Categorys();
    				 newCategory.setName(productdto.getCategory());
    				 return categoryRepository.save(newCategory);
    				 
    			 });
    	  
    	 Product product = Product
    			 .builder()
    			 .description(productdto.getDescription())
    			 .name(productdto.getName())
    			 .imageUrl(productdto.getImageUrl())
    			 .stockQuantity(productdto.getStockQuantity())
    			 .price(productdto.getPrice())
    			 .category(category)
    			 .build();
    	 
    	Product savedProduct =  productRepository.save(product);
    	
    	return mapToResponseDto(savedProduct);
    	 
     }
     
     private ResponseProductDto mapToResponseDto(Product product) {
         return ResponseProductDto.builder()
                 .id(product.getId()) 
                 .name(product.getName())
                 .description(product.getDescription())
                 .price(product.getPrice())
                 .stockQuantity(product.getStockQuantity())
                 .categoryName(product.getCategory().getName()) 
                 .build();
     }

}
