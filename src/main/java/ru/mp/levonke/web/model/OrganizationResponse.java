package ru.mp.levonke.web.model;

import lombok.Data;
import ru.mp.levonke.domain.Organization;
import ru.mp.levonke.domain.User;

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
		this.website = organization.getPubEmail();
		this.owner = organization.getOwner();
	}
}
