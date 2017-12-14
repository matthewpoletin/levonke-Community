package com.levonke.Community.web.model;

import com.levonke.Community.domain.Organization;

import com.levonke.Community.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationResponse {
	Integer id;
	String name;
	String officialName;
	String description;
	String pubEmail;
	String website;
	Integer ownerId;

	public OrganizationResponse(Organization organization) {
		this.id = organization.getId();
		this.name = organization.getName();
		this.officialName = organization.getOfficialName();
		this.description = organization.getDescription();
		this.pubEmail = organization.getPubEmail();
		this.website = organization.getWebsite();
		this.ownerId = organization.getOwner() != null ? organization.getOwner().getId() : null;
	}
}
