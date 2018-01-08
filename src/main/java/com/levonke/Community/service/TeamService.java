package com.levonke.Community.service;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.Team;
import com.levonke.Community.web.model.TeamRequest;

import org.springframework.data.domain.Page;
import java.util.List;

public interface TeamService {
	Page<Team> getTeams(Integer page, Integer size);
	Page<Team> getTeamsWithName(String name, Integer page, Integer size);
	Team createTeam(TeamRequest teamRequest);
	Team getTeamById(Integer teamId);
	Team getTeamByName(String name);
	Team updateUserById(Integer teamId, TeamRequest teamRequest);
	void deleteTeamById(Integer teamId);
	
	void setOrganizationOfTeam(Integer teamId, Integer organizationId);
	
	List<Integer> getUsers(Integer teamId);
	void addUser(Integer teamId, Integer userId);
	void removeUser(Integer teamId, Integer userId);
	
	Organization getOrganizationOfTeam(Integer teamId);
}
