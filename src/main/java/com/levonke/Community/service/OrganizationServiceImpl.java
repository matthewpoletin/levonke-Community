package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.OrganizationRequest;
import com.levonke.Community.domain.Organization;
import com.levonke.Community.repository.OrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private final OrganizationRepository organizationRepository;
	private final UserServiceImpl userService;

	@Autowired
	public OrganizationServiceImpl(OrganizationRepository organizationRepository, UserServiceImpl userService) {
		this.organizationRepository = organizationRepository;
		this.userService = userService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organization> getOrganizations(Integer page, Integer size) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 25;
		}
		return organizationRepository.findAll(new PageRequest(page, size)).getContent();
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
		Organization organization = organizationRepository.findById(organizationId).get();
		if (organization == null) {
			throw new EntityNotFoundException("Organization '{" + organizationId + "}' not found");
		}
		return organization;
	}

	@Override
	@Transactional
	public Organization update(Integer organizationId, OrganizationRequest organizationRequest) {
		Organization organization = organizationRepository.findById(organizationId).get();
		if (organization == null) {
			throw new EntityNotFoundException("Organization '{" + organizationId + "}' not found");
		}
		organization.setName(organizationRequest.getName() != null ? organizationRequest.getName() : organization.getName());
		organization.setDescription(organizationRequest.getDescription() != null ? organizationRequest.getDescription() : organization.getDescription());
		organization.setPubEmail(organizationRequest.getPubEmail() != null ? organizationRequest.getPubEmail() : organization.getPubEmail());
		organization.setWebsite(organizationRequest.getWebsite() != null ? organizationRequest.getWebsite() : organization.getWebsite());
		if (organizationRequest.getOwnerId() != null) {
			User user = userService.getUserById(organizationRequest.getOwnerId());
			if (user != null) {
				organization.setOwner(user);
			}
		}
		return organizationRepository.save(organization);
	}

	@Override
	public void delete(Integer organizationId) {
		organizationRepository.deleteById(organizationId);
	}

}
