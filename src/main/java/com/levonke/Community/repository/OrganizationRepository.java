package com.levonke.Community.repository;

import com.levonke.Community.domain.Organization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository
		extends JpaRepository<Organization, Integer> {
	
	Organization getOrganizationByNameIgnoreCase(String name);
	
	Page<Organization> getOrganizationsByNameContainingIgnoreCase(String name, Pageable pageable);
	
}
