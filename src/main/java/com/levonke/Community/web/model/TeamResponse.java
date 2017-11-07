package com.levonke.Community.web.model;

import com.levonke.Community.domain.Team;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamResponse {
	private Integer id;
	private String name;
	private Integer organizationId;
	private List<Integer> usersId = new ArrayList<>();

	public TeamResponse(Team team) {
		this.id = team.getId();
		this.name = team.getName();
		this.organizationId = team.getOrganization().getId();
		team.getUsers().forEach(user -> this.usersId.add(user.getId()));
	}
}
