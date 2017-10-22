package ru.mp.levonke.service;

import ru.mp.levonke.domain.Organization;
import ru.mp.levonke.web.model.OrganizationRequest;

public interface OrganizationService {
	Iterable<Organization> getOrganizations();
	Organization create(OrganizationRequest organizationRequest);
	Organization read(Integer organizationId);
	Organization update(Integer organizationId, OrganizationRequest organizationRequest);
	void delete(Integer organizationId);
}
