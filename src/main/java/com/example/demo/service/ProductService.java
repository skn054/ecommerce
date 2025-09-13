package com.example.demo.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ResponseProductDto;
import com.example.demo.exception.ResourceNotFoundException;
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
     
     public ResponseProductDto getProductById(Long id) {
    	    Product product =  getProductByIdInternal(id);
    	    return ResponseProductDto.mapToResponseDto(product);
     }
     
     public Product getProductByIdInternal(Long id) { // A helper method to get the entity
         return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
     
     public ResponseProductDto updateProductById(Long id,CreateProductDto productDto) {
    	 Categorys category = categoryRepository.findByNameIgnoreCase(productDto.getCategory())
    			 .orElseThrow(() -> new ResourceNotFoundException("Category not found with name : " + productDto.getCategory()));
    	 Product productToUpdate = getProductByIdInternal(id);
    	 productToUpdate.setName(productDto.getName());
    	 productToUpdate.setDescription(productDto.getDescription());
    	    productToUpdate.setPrice(productDto.getPrice());
    	    productToUpdate.setStockQuantity(productDto.getStockQuantity());
    	    productToUpdate.setImageUrl(productDto.getImageUrl());
    	    productToUpdate.setCategory(category);
    	 
    	 Product savedProduct = productRepository.save(productToUpdate);
    	 return ResponseProductDto.mapToResponseDto(savedProduct);
     }
     
     
     public void deleteProductById(Long id) {
    	 
    	 if(!productRepository.existsById(id)) {
    		 throw new ResourceNotFoundException("Product not found with id: " + id);
    	 }
    	 productRepository.deleteById(id);
     }
     
     public List<ResponseProductDto> searchProductByKeyword(String keyword) {
    	 
    	List<Product> searchProducts = productRepository.searchByKeyword(keyword);
    	
    	return searchProducts.stream().map(ResponseProductDto::mapToResponseDto).collect(Collectors.toList());
    	
     }
   

}
