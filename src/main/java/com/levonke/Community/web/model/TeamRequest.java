package com.levonke.Community.web.model;

import lombok.Data;

@Data
public class TeamRequest {
	private Integer id;
	private String name;
	// TODO: make integers for organizations and users
//	private Organization organization;
//	private Collection<User> team = new ArrayList<User>();
}
