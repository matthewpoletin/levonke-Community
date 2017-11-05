package com.levonke.Community.web;

import com.levonke.Community.domain.Team;
import com.levonke.Community.service.TeamServiceImpl;
import com.levonke.Community.web.model.TeamRequest;
import com.levonke.Community.web.model.TeamResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(TeamController.TEAM_BASE_URI)
public class TeamController {

	public static final String TEAM_BASE_URI = "/api/community/teams";

	private TeamServiceImpl teamService;

	@Autowired
	TeamController(TeamServiceImpl teamService) {
		this.teamService = teamService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TeamResponse> getTeams(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		return teamService.getTeams(page, size)
			.stream()
			.map(TeamResponse::new)
			.collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createTeam(@RequestBody TeamRequest teamRequest, HttpServletResponse response) {
		Team team = teamService.create(teamRequest);
		response.addHeader(HttpHeaders.LOCATION, this.TEAM_BASE_URI + "/" + team.getId());
	}

	@RequestMapping(value = "/{teamId}", method = RequestMethod.GET)
	public TeamResponse getTeam(@PathVariable("teamId") final Integer teamId) {
		return new TeamResponse(teamService.read(teamId));
	}

	@RequestMapping(value = "/{teamId}", method = RequestMethod.PATCH)
	public TeamResponse updateTeam(@PathVariable("teamId") final Integer teamId, @RequestBody TeamRequest teamRequest) {
		return new TeamResponse(teamService.update(teamId, teamRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{teamId}", method = RequestMethod.DELETE)
	public void deleteTeam(@PathVariable("teamId") final Integer teamId) {
		teamService.delete(teamId);
	}

}
