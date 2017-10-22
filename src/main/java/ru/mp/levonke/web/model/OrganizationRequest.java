package ru.mp.levonke.web.model;

import lombok.Data;

import ru.mp.levonke.domain.User;

@Data
public class OrganizationRequest {
	Integer id;
	String name;
	String description;
	String pubEmail;
	String website;
	User owner;
}
