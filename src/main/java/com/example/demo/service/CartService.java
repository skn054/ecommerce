package com.example.demo.service;
import com.example.demo.repository.CartItemRepoistory;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CartItemDto;
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
    
    private final CartItemRepoistory cartItemRepoistory;

    public CartService(UserRepository userRepository, CartRepository cartRepository,ProductRepository productRepository,CartItemRepoistory cartItemRepoistory) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepoistory = cartItemRepoistory;
        
    }
	
	
	
	public void addProductToCart(Long UserId, CartItemDto cartItemDto) {
		
		//check if user exists? if doesn't throw exception. 
		// If user exists, check if cart exists for a user? If cart exists then check if product and exist. If stock exists.
		//If doesnt create a cart. 
		// check if product exists and quantity of product worth of stock exists.
		// add product to cart.
	
		
		User user = userRepository.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User not found with id " + UserId));
		
		Optional<Cart> cart =  cartRepository.findByUserId(UserId);
		if(cart.isEmpty()) {
			// create a cart and add product
			Cart c = Cart.builder()
					.user(user)
					.build();
			Optional<Product> product = productRepository.findById(cartItemDto.getProductId());
			if(product.isEmpty()) {
				throw new ResourceNotFoundException("Product not found with id"+ cartItemDto.getProductId());
			}
			CartItem item = CartItem.builder().cart(c).product(product.get()).quantity(cartItemDto.getQuantity()).build();
			
			c.setCartItems(Arrays.asList(item));
			
			cartRepository.save(c);
			
			
			
		}else {
			
			 Optional<CartItem> cartItemOptional =  cartItemRepoistory.findByUserAndProduct(UserId, cartItemDto.getProductId());
			 if(cartItemOptional.isEmpty()) {
				 //create a new cart item and add product
				 Optional<Product> product = productRepository.findById(cartItemDto.getProductId());
					if(product.isEmpty()) {
						throw new ResourceNotFoundException("Product not found with id"+ cartItemDto.getProductId());
					}
					CartItem item = CartItem.builder().cart(cart.get()).product(product.get()).quantity(cartItemDto.getQuantity()).build();
					
					cartItemRepoistory.save(item);
			 }
			 else {
				 // update quantity of product.
				 CartItem cartItem = cartItemOptional.get();
				 cartItem.setQuantity(cartItemDto.getQuantity());
				 cartItemRepoistory.save(cartItem);
			 }
			
		}
		
		
		
	}

}
