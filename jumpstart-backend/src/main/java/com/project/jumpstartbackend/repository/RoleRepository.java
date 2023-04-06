package com.project.jumpstartbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jumpstartbackend.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{


}