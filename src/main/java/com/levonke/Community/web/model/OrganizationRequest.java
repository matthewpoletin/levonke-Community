package com.levonke.Community.web.model;

import com.levonke.Community.domain.User;
import lombok.Data;

@Data
public class OrganizationRequest {
	String name;
	String description;
	String pubEmail;
	String website;
	User owner;
}
