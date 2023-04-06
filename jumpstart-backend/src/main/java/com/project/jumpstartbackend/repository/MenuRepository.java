package com.project.jumpstartbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.jumpstartbackend.entity.Menu;



@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

	@Query(value = "SELECT * FROM menu_tb WHERE status = 1", nativeQuery = true)
	public List<Menu> findByStatusTrue();
	@Query(value = "SELECT * FROM menu_tb WHERE partner_id = :id", nativeQuery = true)
	public List<Menu> findByPartner(@Param("id") Long id);
}
