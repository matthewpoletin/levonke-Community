package com.levonke.Community.service;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.web.model.OrganizationRequest;

public interface OrganizationService {
	Iterable<Organization> getOrganizations();
	Organization create(OrganizationRequest organizationRequest);
	Organization read(Integer organizationId);
	Organization update(Integer organizationId, OrganizationRequest organizationRequest);
	void delete(Integer organizationId);
}
