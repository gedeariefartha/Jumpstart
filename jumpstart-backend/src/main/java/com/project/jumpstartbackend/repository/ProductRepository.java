package com.project.jumpstartbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.jumpstartbackend.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "SELECT * FROM product_tb WHERE status = 1", nativeQuery = true)
	public List<Product> findByStatusTrue();
	@Query(value = "SELECT * FROM product_tb WHERE partner_id = :id", nativeQuery = true)
	public List<Product> findByStaff(@Param("id") Long id);
	@Query("SELECT p FROM Product p WHERE name=:productname")
    public List<Product> seachByProductName(String productname);
}
