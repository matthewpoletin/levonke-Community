package ru.mp.levonke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mp.levonke.domain.Organization;
import ru.mp.levonke.repository.OrganizationRepository;
import ru.mp.levonke.web.model.OrganizationRequest;

import javax.persistence.EntityNotFoundException;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private final OrganizationRepository organizationRepository;

	@Autowired
	public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Organization> getOrganizations() {
		return organizationRepository.findAll();
	}

	@Override
	@Transactional
	public Organization create(OrganizationRequest organizationRequest) {
		Organization organization = new Organization()
			.setName(organizationRequest.getName())
			.setDescription(organizationRequest.getDescription())
			.setPubEmail(organizationRequest.getPubEmail())
			.setWebsite(organizationRequest.getWebsite());
		return organizationRepository.save(organization);
	}

	@Override
	@Transactional(readOnly = true)
	public Organization read(Integer organizationId) {
		Organization organization = organizationRepository.findOne(organizationId);
		if (organization == null) {
			throw new EntityNotFoundException("Organization '{" + organizationId + "}' not found");
		}
		return organization;
	}

	@Override
	@Transactional
	public Organization update(Integer organizationId, OrganizationRequest organizationRequest) {
		Organization organization = organizationRepository.findOne(organizationId);
		if (organization == null) {
			throw new EntityNotFoundException("Organization '{" + organizationId + "}' not found");
		}
		organization.setName(organizationRequest.getName() != null ? organizationRequest.getName() : organization.getName());
		organization.setDescription(organizationRequest.getDescription() != null ? organizationRequest.getDescription() : organization.getDescription());
		organization.setPubEmail(organizationRequest.getPubEmail() != null ? organizationRequest.getPubEmail() : organization.getPubEmail());
		organization.setWebsite(organizationRequest.getWebsite() != null ? organizationRequest.getWebsite() : organization.getWebsite());
		organization.setOwner(organizationRequest.getOwner() != null ? organizationRequest.getOwner() : organization.getOwner());
		return organizationRepository.save(organization);
	}

	@Override
	public void delete(Integer organizationId) {
		organizationRepository.delete(organizationId);
	}

}
