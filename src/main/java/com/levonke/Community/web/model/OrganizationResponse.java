package com.levonke.Community.web.model;

import com.levonke.Community.domain.Organization;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrganizationResponse {
	Integer id;
	String name;
	String description;
	String pubEmail;
	String website;
	Integer ownerId;

	public OrganizationResponse(Organization organization) {
		this.id = organization.getId();
		this.name = organization.getName();
		this.description = organization.getName();
		this.pubEmail = organization.getPubEmail();
		this.website = organization.getWebsite();
		this.ownerId = organization.getOwner().getId();
	}
}
