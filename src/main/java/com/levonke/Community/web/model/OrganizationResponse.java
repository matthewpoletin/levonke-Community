package com.levonke.Community.web.model;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.User;
import lombok.Data;

@Data
public class OrganizationResponse {
	Integer id;
	String name;
	String description;
	String pubEmail;
	String website;
	User owner;

	public OrganizationResponse(Organization organization) {
		this.id = organization.getId();
		this.name = organization.getName();
		this.description = organization.getName();
		this.pubEmail = organization.getPubEmail();
		this.website = organization.getWebsite();
		this.owner = organization.getOwner();
	}
}
