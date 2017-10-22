package ru.mp.levonke.service;

import ru.mp.levonke.domain.Team;
import ru.mp.levonke.web.model.TeamRequest;

public interface TeamService {
	Iterable<Team> getTeams();
	Team create(TeamRequest teamRequest);
	Team read(Integer teamId);
	Team update(Integer teamId, TeamRequest teamRequest);
	void delete(Integer teamId);
}
