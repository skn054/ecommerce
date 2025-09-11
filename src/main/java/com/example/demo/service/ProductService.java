package com.example.demo.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

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
    	
    	return ResponseProductDto.mapToResponseDto(savedProduct);
    	
    	 
     }
     
     public List<ResponseProductDto> getAllProducts() {
    	  List<Product> products =   productRepository.findAll();
    	  
    	  return products.stream().map(ResponseProductDto::mapToResponseDto).collect(Collectors.toList());
    	  
     }
     
     public Product getProductById(Long id) {
    	    return productRepository.findById(id).orElseThrow();
     }
     
     public ResponseProductDto updateProductById(Long id,CreateProductDto productDto) {
    	 Categorys categorys = categoryRepository.findByNameIgnoreCase(productDto.getCategory()).orElseThrow();
    	 Product product = productRepository.findById(id).orElseThrow();
    	 Product updatedProduct = CreateProductDto.mapToProduct(productDto, categorys);
    	 updatedProduct.setId(id);
    	 Product savedProduct = productRepository.save(updatedProduct);
    	 return ResponseProductDto.mapToResponseDto(savedProduct);
     }
     
   

}
