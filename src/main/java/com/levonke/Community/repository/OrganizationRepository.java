package com.levonke.Community.repository;

import com.levonke.Community.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository
		extends JpaRepository<Organization, Integer> {

}
