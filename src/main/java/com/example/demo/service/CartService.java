package com.example.demo.service;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CartItemDto;
import com.example.demo.dto.CartResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import com.example.demo.models.User;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;
    
    private final ProductRepository productRepository;
    
    private final CartItemRepository cartItemRepoistory;

    public CartService(UserRepository userRepository, CartRepository cartRepository,ProductRepository productRepository,CartItemRepository cartItemRepoistory) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepoistory = cartItemRepoistory;
        
    }
	
	
	
    @Transactional
	public void addProductToCart(Long userId, CartItemDto cartItemDto) {
		
		//check if user exists? if doesn't throw exception. 
		// If user exists, check if cart exists for a user? If cart exists then check if product and exist. If stock exists.
		//If doesnt create a cart. 
		// check if product exists and quantity of product worth of stock exists.
		// add product to cart.
	
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
		
		Product product = productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + cartItemDto.getProductId()));
		
		if(product.getStockQuantity() < cartItemDto.getQuantity()) {
			 throw new RuntimeException("Not enough stock for product: " + product.getName());
		}
		
		 Cart cart = cartRepository.findByUser(user)
	                .orElseGet(() -> {
	                    Cart newCart = Cart.builder().user(user).build();
	                    return cartRepository.save(newCart);
	                });
		 
		 Optional<CartItem> existingCartItemOpt = cartItemRepoistory.findByCartAndProduct(cart, product);
		 if(existingCartItemOpt.isPresent()) {
			 
			 CartItem existingItem = existingCartItemOpt.get();
			 existingItem.setQuantity(existingItem.getQuantity() + cartItemDto.getQuantity());
			 cartItemRepoistory.save(existingItem);
		 }else {
			 
			 CartItem newCartItem = CartItem.builder()
					 .product(product)
					 .quantity(cartItemDto.getQuantity())
					 .cart(cart)
					 .build();
			 
			 cartItemRepoistory.save(newCartItem);
			 
			 
		 }
		 		
		
	}
    
    public CartResponseDto getCart(Long userId) {
    	
    	User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id" + userId));
    	
    	Cart cart = cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Cart not found for user with id "+ userId));
    	
    	return CartResponseDto.maptoCartResponseDto(cart);
    	
    }
    
    public void deleteCart(Long userId) {
    	
    	User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id"+ userId));
    	Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart not found for user with id"+ userId));
    	
    	cartRepository.delete(cart);
    }

}
