package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
import com.levonke.Community.web.model.TeamRequest;

import java.util.List;

public interface TeamService {
	List<Team> getTeams(Integer size, Integer page);
	Team create(TeamRequest teamRequest);
	Team read(Integer teamId);
	Team update(Integer teamId, TeamRequest teamRequest);
	void delete(Integer teamId);
}
