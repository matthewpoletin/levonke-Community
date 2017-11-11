package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrganizationRequest {
	String name;
	String description;
	String pubEmail;
	String website;
	Integer ownerId;
}
