package com.project.jumpstartbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.jumpstartbackend.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query(value = "SELECT * FROM order_tb WHERE member_id = :id and status = 0", nativeQuery = true)
	public List<Order> findByMember(@Param("id") Long id);
	@Query(value = "SELECT * FROM order_tb WHERE status = 1", nativeQuery = true)
	public List<Order> findByStatusTrue();
}
