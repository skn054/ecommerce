package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	public Optional<CartItem> findByCartAndProduct(Cart cart,Product product);

	
}
