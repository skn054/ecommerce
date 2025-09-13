package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class User extends BaseModel{
	
	private String firstName;
	private String lastName;
	private String email;
	
	private String phone;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	
	
	
	

}
