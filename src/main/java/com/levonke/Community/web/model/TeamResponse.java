package com.levonke.Community.web.model;

import lombok.Data;
import com.levonke.Community.domain.Team;

@Data
public class TeamResponse {
	private Integer id;
	private String name;
//	private Organization organization;
//	private Collection<User> team = new ArrayList<User>();

	public TeamResponse(Team team) {
		this.id = team.getId();
		this.name = team.getName();
		// TODO: add workout for organization and users
	}
}
