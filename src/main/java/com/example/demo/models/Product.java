package com.example.demo.models;

import jakarta.persistence.Entity;
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
public class Product extends BaseModel{
	
	private String name;
	
	private String description;
	
	private Long stockQuantity;
	
	private String imageUrl;
	
	private Long price;
	
	
	
	
	

}
