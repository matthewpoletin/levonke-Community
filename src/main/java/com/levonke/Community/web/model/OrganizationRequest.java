package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class OrganizationRequest {
	
	@NotEmpty(message = "Not valid name")
	String name;
	
	String officialName;
	
	String description;
	
	String pubEmail;
	
	String website;
	
	Integer ownerId;
	
}
