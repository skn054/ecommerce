package com.example.demo.models;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private String street;
	private String state;
	private String country;
	private String zipcode;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore // Prevents infinite recursion when serializing User -> Address -> User
	private User user;
	

}
