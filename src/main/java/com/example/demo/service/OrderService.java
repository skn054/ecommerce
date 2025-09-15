package com.example.demo.service;

import com.example.demo.dto.OrderResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Cart;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.models.OrderStatus;
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
				.status(OrderStatus.PROCESSING)
				.build();
		
		List<OrderItem> orderItems = cart.getCartItems().stream().map(item->{
			
			OrderItem orderItem = OrderItem.builder()
					.order(order)
					.product(item.getProduct())
					.quantity(item.getQuantity())
					.price(item.getProduct().getPrice())
					.build();
			
			return orderItem;
		}).collect(Collectors.toList());
		
		//calculate order price;
		BigDecimal totalPrice =  orderItems.stream().map(item ->  BigDecimal.valueOf(item.getQuantity())
				.multiply(item.getPrice()))
				.reduce(BigDecimal.ZERO,(a,b)->a.add(b));
		
		order.setTotalAmount(totalPrice);
		order.setOrderItems(orderItems);
		
		Order placedOrder = orderRepository.save(order);
		
		return OrderResponseDto.mapToOrderDto(order);
		
		
		
	}

}
