package com.levonke.Community.service;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.web.model.OrganizationRequest;

import java.util.List;

public interface OrganizationService {
	List<Organization> getOrganizations(Integer page, Integer size);
	Organization create(OrganizationRequest organizationRequest);
	Organization read(Integer organizationId);
	Organization update(Integer organizationId, OrganizationRequest organizationRequest);
	void delete(Integer organizationId);
}
