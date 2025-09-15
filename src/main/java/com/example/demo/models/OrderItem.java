package com.example.demo.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class OrderItem extends BaseModel{
	
	 @ManyToOne
	 @JoinColumn(name = "order_id")
	 @JsonIgnore
	 private Order order;
	 
	 @ManyToOne
	 @JoinColumn(name = "product_id")
	 private Product product;
	 
	 private Long quantity;
	 
	 private BigDecimal price; 

}
