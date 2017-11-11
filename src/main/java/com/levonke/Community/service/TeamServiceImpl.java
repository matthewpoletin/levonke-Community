package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
import com.levonke.Community.repository.TeamRepository;
import com.levonke.Community.web.model.TeamRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
	
	private final TeamRepository teamRepository;
	
	private final OrganizationServiceImpl organizationService;
	
	private final UserServiceImpl userService;
	
	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository, OrganizationServiceImpl organizationService, UserServiceImpl userService) {
		this.teamRepository = teamRepository;
		this.organizationService = organizationService;
		this.userService = userService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Team> getTeams(Integer page, Integer size) {
		if(page == null)
			page = 0;
		if (size == null) {
			size = 25;
		}
		return teamRepository.findAll(PageRequest.of(page, size)).getContent();
	}
	
	@Override
	@Transactional
	public Team createTeam(TeamRequest teamRequest) {
		Team team = new Team()
			.setName(teamRequest.getName());
		this.setOrganizationForTeam(team.getId(), teamRequest.getOrganizationId());
		return teamRepository.save(team);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Team getTeamById(Integer teamId) {
		return teamRepository.findById(teamId)
			.orElseThrow(() -> new EntityNotFoundException("Team '{" + teamId + "}' not found"));
	}
	
	@Override
	@Transactional
	public Team updateUserById(Integer teamId, TeamRequest teamRequest) {
		Team team = this.getTeamById(teamId);
		team.setName(teamRequest.getName() != null ? teamRequest.getName() : team.getName());
		this.setOrganizationForTeam(teamId, teamRequest.getOrganizationId());
		return teamRepository.save(team);
	}
	
	@Override
	@Transactional
	public void deleteTeamById(Integer teamId) {
		teamRepository.deleteById(teamId);
	}
	
	@Override
	@Transactional
	public void setOrganizationForTeam(Integer teamId, Integer organizationId) {
		Team team = this.getTeamById(teamId);
		team.setOrganization(organizationService.getOrganizationById(organizationId));
		teamRepository.save(team);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Integer> getUsers(Integer teamId) {
		List<Integer> usersId = new ArrayList<>();
		this.getTeamById(teamId).getUsers().forEach(user -> usersId.add(user.getId()));
		return usersId;
	}
	
	@Override
	@Transactional
	public void addUser(Integer teamId, Integer userId) {
		Team team = this.getTeamById(teamId);
		team.getUsers().add(userService.getUserById(userId));
		teamRepository.save(team);
	}
	
	@Override
	@Transactional
	public void removeUser(Integer teamId, Integer userId) {
		Team team = this.getTeamById(teamId);
		team.getUsers().remove(userService.getUserById(userId));
		teamRepository.save(team);
	}
	
}
