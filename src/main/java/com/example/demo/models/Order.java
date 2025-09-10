package com.example.demo.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order extends BaseModel{
	
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	 private List<OrderItem> orderItems = new ArrayList<>();
	 
	 
	 @ManyToOne
	 @JoinColumn(name = "shipping_address_id")
	 private Address shippingAddress;
	 
	 private BigDecimal totalAmount; 
	 
	 @Enumerated(EnumType.STRING)
	 private OrderStatus status;
	 
	 
	 
	

}
