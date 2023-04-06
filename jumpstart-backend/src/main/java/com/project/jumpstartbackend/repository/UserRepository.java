package com.project.jumpstartbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.jumpstartbackend.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user_tb WHERE role = 'member'", nativeQuery = true)
	public List<User> findByRoleMember();
	@Query(value = "SELECT * FROM user_tb WHERE role = 'rider'", nativeQuery = true)
	public List<User> findByRoleRider();
    public Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}