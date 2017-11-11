package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
import com.levonke.Community.web.model.TeamRequest;

import java.util.List;

public interface TeamService {
	List<Team> getTeams(Integer size, Integer page);
	Team createTeam(TeamRequest teamRequest);
	Team getTeamById(Integer teamId);
	Team updateUserById(Integer teamId, TeamRequest teamRequest);
	void deleteTeamById(Integer teamId);
	
	void setOrganizationForTeam(Integer teamId, Integer organizationId);
	
	List<Integer> getUsers(Integer teamId);
	void addUser(Integer teamId, Integer userId);
	void removeUser(Integer teamId, Integer userId);
}
