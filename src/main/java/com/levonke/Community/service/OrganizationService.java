package com.levonke.Community.service;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.OrganizationRequest;

import java.util.List;

public interface OrganizationService {
	List<Organization> getOrganizations(Integer page, Integer size);
	Organization createOrganization(OrganizationRequest organizationRequest);
	Organization getOrganizationById(Integer organizationId);
	Organization updateOrganizationById(Integer organizationId, OrganizationRequest organizationRequest);
	void deleteOrganizationById(Integer organizationId);
	
	void setOwnerToOrganization(Integer organizationId, Integer userId);
	User getOwnerOfOrganization(Integer organizationId);
}
