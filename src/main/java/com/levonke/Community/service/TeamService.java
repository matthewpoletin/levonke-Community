package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
import com.levonke.Community.web.model.TeamRequest;

public interface TeamService {
	Iterable<Team> getTeams();
	Team create(TeamRequest teamRequest);
	Team read(Integer teamId);
	Team update(Integer teamId, TeamRequest teamRequest);
	void delete(Integer teamId);
}
