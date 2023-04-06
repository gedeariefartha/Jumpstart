package com.project.jumpstartbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.jumpstartbackend.entity.Campaign;



@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{

	public List<Campaign> findByStatusTrue();
	
	public List<Campaign> findByStatusFalse();
}