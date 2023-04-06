package com.project.jumpstartbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.project.jumpstartbackend.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query(value = "SELECT * FROM order_tb WHERE staff_id = :id and status = 0", nativeQuery = true)
	public List<Payment> findByStaff(@Param("id") Long id);
	@Query(value = "SELECT * FROM payment_tb WHERE status = 1", nativeQuery = true)
	public List<Payment> findByStatusTrue();
}


