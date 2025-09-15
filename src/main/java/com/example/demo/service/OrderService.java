package com.example.demo.service;

import com.example.demo.dto.OrderResponseDto;
import com.example.demo.exception.InsufficientStockException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Cart;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;


    OrderService(UserRepository userRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }
	
	
    
    @Transactional
	public OrderResponseDto placeOrder(Long userId) {
		
		// get cart for user. if cart not presentthrow wxception.
		// get items from cart and cteate order itmes and persists in db.
		// calcuate total order value and create a new order and
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id" + userId));
		
		Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart Empty for user with id"+ userId));
		
		Order order = Order.builder()
				.user(user)
				.shippingAddress(user.getAddresses().get(0))
				.status(OrderStatus.PENDING)
				.build();
		
		cart.getCartItems().forEach(item->{
			
			OrderItem orderItem = OrderItem.builder()
					.order(order)
					.product(item.getProduct())
					.quantity(item.getQuantity())
					.price(item.getProduct().getPrice())
					.build();
			
			order.addOrderItem(orderItem);
		});
		
		
		
		order.getOrderItems().forEach(orderItem -> {
			Product product = orderItem.getProduct();
			
			if(product.getStockQuantity() < orderItem.getQuantity()) {
				throw new InsufficientStockException("Not enough stock for " + product.getName() + " while placing order.");
			}
			
			long newStock = product.getStockQuantity() - orderItem.getQuantity();
			product.setStockQuantity(newStock);
			
			/// No need to call productRepository.save(product) here!
		    // Because the method is @Transactional, Hibernate's "dirty checking"
		    // will automatically detect the change to the product entity
		    // and include it in the final transaction commit.
		});
		
		
		//calculate order price;
		BigDecimal totalPrice =  order.getOrderItems().stream()
						.map(item ->  item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
						.reduce(BigDecimal.ZERO,(a,b)->a.add(b));
				
		order.setTotalAmount(totalPrice);
		Order placedOrder = orderRepository.save(order);
		
		cartRepository.delete(cart);
		
		return OrderResponseDto.mapToOrderDto(order);
		
		
		
	}

}
