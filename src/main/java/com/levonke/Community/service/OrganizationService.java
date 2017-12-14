package com.levonke.Community.service;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.Team;
import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.OrganizationRequest;

import org.springframework.data.domain.Page;

public interface OrganizationService {
	Page<Organization> getOrganizations(Integer page, Integer size);
	Page<Organization> getOrganizationsWithName(String name, Integer page, Integer size);
	Organization createOrganization(OrganizationRequest organizationRequest);
	Organization getOrganizationById(Integer organizationId);
	Organization getOrganizationByName(String name);
	Organization updateOrganizationById(Integer organizationId, OrganizationRequest organizationRequest);
	void deleteOrganizationById(Integer organizationId);
	
	void setOwnerToOrganization(Integer organizationId, Integer userId);
	User getOwnerOfOrganization(Integer organizationId);
	
	Page<Team> getTeamsOfOrganization(Integer organizationId, Integer page, Integer size);
}
