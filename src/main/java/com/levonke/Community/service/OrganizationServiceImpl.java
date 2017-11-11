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
		return organizationRepository.findAll(PageRequest.of(page, size)).getContent();
	}
	
	@Override
	@Transactional
	public Organization createOrganization(OrganizationRequest organizationRequest) {
		Organization organization = new Organization()
			.setName(organizationRequest.getName())
			.setDescription(organizationRequest.getDescription())
			.setPubEmail(organizationRequest.getPubEmail())
			.setWebsite(organizationRequest.getWebsite());
		return organizationRepository.save(organization);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Organization getOrganizationById(Integer organizationId) {
		return organizationRepository.findById(organizationId)
			.orElseThrow(() -> new EntityNotFoundException("Organization '{" + organizationId + "}' not found"));
	}
	
	@Override
	@Transactional
	public Organization updateOrganizationById(Integer organizationId, OrganizationRequest organizationRequest) {
		Organization organization = this.getOrganizationById(organizationId);
		organization.setName(organizationRequest.getName() != null ? organizationRequest.getName() : organization.getName());
		organization.setDescription(organizationRequest.getDescription() != null ? organizationRequest.getDescription() : organization.getDescription());
		organization.setPubEmail(organizationRequest.getPubEmail() != null ? organizationRequest.getPubEmail() : organization.getPubEmail());
		organization.setWebsite(organizationRequest.getWebsite() != null ? organizationRequest.getWebsite() : organization.getWebsite());
		this.setOwnerToOrganization(organizationId, organizationRequest.getOwnerId());
		return organizationRepository.save(organization);
	}
	
	@Override
	@Transactional
	public void deleteOrganizationById(Integer organizationId) {
		organizationRepository.deleteById(organizationId);
	}
	
	@Override
	@Transactional
	public void setOwnerToOrganization(Integer organizationId, Integer userId) {
		Organization organization = this.getOrganizationById(organizationId);
		User user = userService.getUserById(userId);
		organization.setOwner(user);
		organizationRepository.save(organization);
	}
	
}
