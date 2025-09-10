package com.example.demo.models;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseModel{
	
	
	private String city;
	private String state;
	private String country;
	private Long zipcode;
	
	@ManyToAny
	@JoinColumn(name = "user_id")
	private User user;
	

}
