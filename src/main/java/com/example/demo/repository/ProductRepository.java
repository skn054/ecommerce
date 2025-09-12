package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	
	@Query("select p from Product p where LOWER(p.name) like LOWER(CONCAT('%', :keyword, '%'))")
	public List<Product> searchByKeyword(@Param("keyword") String keyword);

}
