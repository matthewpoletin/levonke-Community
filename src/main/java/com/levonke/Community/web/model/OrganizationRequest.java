package com.levonke.Community.web.model;

import lombok.Data;

@Data
public class OrganizationRequest {
	String name;
	String description;
	String pubEmail;
	String website;
	Integer ownerId;
}
