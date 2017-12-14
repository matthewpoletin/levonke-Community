package com.levonke.Community.repository;

import com.levonke.Community.domain.Team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository
		extends JpaRepository<Team, Integer> {
	
	Team getTeamByNameIgnoreCase(String name);
	
	Page<Team> getTeamsByNameContainingIgnoreCase(String name, Pageable pageable);
	
}
