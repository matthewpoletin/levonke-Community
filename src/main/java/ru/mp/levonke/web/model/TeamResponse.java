package ru.mp.levonke.web.model;

import lombok.Data;
import ru.mp.levonke.domain.Team;

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
