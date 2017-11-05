package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
import com.levonke.Community.repository.TeamRepository;
import com.levonke.Community.web.model.TeamRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;

	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Team> getTeams(Integer page, Integer size) {
		if(page == null)
			page = 0;
		if (size == null) {
			size = 25;
		}
		return teamRepository.findAll(new PageRequest(page, size)).getContent();
	}

	@Override
	@Transactional
	public Team create(TeamRequest teamRequest) {
		Team team = new Team()
			.setName(teamRequest.getName());
//			.setOrganization();
		// TODO: add setting organization and users
		return team;
	}

	@Override
	@Transactional(readOnly = true)
	public Team read(Integer teamId) {
		Team team = teamRepository.findOne(teamId);
		if (team == null) {
			throw new EntityNotFoundException("Team '{" + teamId + "}' not found");
		}
		return team;
	}

	@Override
	@Transactional
	public Team update(Integer teamId, TeamRequest teamRequest) {
		Team team = teamRepository.findOne(teamId);
		if (team == null) {
			throw new EntityNotFoundException("Team '{" + teamId + "}' not found");
		}
		team.setName(teamRequest.getName() != null ? teamRequest.getName() : team.getName());
		// TODO: add detting organizations and users
		return teamRepository.save(team);
	}

	@Override
	@Transactional
	public void delete(Integer teamId) {
		teamRepository.delete(teamId);
	}

}
