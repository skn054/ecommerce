package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart extends BaseModel{
	
	 @OneToOne
	 @JoinColumn(name = "user_id")
	  private User user;
	 
	 @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	 private List<CartItem> cartItems = new ArrayList<>();
	 
	

}
