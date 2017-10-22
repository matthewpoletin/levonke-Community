package ru.mp.levonke.repository;

import org.springframework.data.repository.CrudRepository;

import ru.mp.levonke.domain.Organization;

public interface OrganizationRepository
		extends CrudRepository<Organization, Integer> {

}
