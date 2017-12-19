package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.OrganizationRequest;
import com.levonke.Community.domain.Organization;
import com.levonke.Community.repository.OrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
	public Page<Organization> getOrganizations(Integer page, Integer size) {
		return organizationRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Organization> getOrganizationsWithName(String name, Integer page, Integer size) {
		return organizationRepository.getOrganizationsByNameContainingIgnoreCase(name, PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Organization createOrganization(OrganizationRequest organizationRequest) {
		Organization organization = new Organization()
			.setName(organizationRequest.getName())
			.setOfficialName(organizationRequest.getOfficialName())
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
	public Organization getOrganizationByName(String name) {
		return organizationRepository.getOrganizationByNameIgnoreCase(name);
	}
	
	@Override
	@Transactional
	public Organization updateOrganizationById(Integer organizationId, OrganizationRequest organizationRequest) {
		Organization organization = this.getOrganizationById(organizationId);
		organization.setName(organizationRequest.getName() != null ? organizationRequest.getName() : organization.getName());
		organization.setOfficialName(organizationRequest.getOfficialName() != null ? organizationRequest.getOfficialName() : organization.getOfficialName());
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
	public void setOwnerToOrganization(Integer organizationId, @NotNull Integer userId) {
		if (userId != null) {
			Organization organization = this.getOrganizationById(organizationId);
			User user = userService.getUserById(userId);
			organization.setOwner(user);
			organizationRepository.save(organization);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getOwnerOfOrganization(Integer organizationId) {
		Organization organization = this.getOrganizationById(organizationId);
		return userService.getUserById(organization.getId());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Team> getTeamsOfOrganization(Integer organizationId, Integer page, Integer size) {
		List<Team> teams = new ArrayList<>(this.getOrganizationById(organizationId).getTeams());
		return new PageImpl<>(teams, PageRequest.of(page, size), teams.size());
	}
	
}
