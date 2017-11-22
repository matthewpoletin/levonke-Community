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
@RequestMapping(TeamController.teamBaseURI)
public class TeamController {

	static final String teamBaseURI = "/api/community";

	private TeamServiceImpl teamService;

	@Autowired
	TeamController(TeamServiceImpl teamService) {
		this.teamService = teamService;
	}
	
	@RequestMapping(value = "/teams", method = RequestMethod.GET)
	public List<TeamResponse> getTeams(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		return teamService.getTeams(page, size)
			.stream()
			.map(TeamResponse::new)
			.collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public TeamResponse createTeam(@RequestBody TeamRequest teamRequest, HttpServletResponse response) {
		Team team = teamService.createTeam(teamRequest);
		response.addHeader(HttpHeaders.LOCATION, teamBaseURI + "/teams/" + team.getId());
		return new TeamResponse(team);
	}

	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.GET)
	public TeamResponse getTeam(@PathVariable("teamId") final Integer teamId) {
		return new TeamResponse(teamService.getTeamById(teamId));
	}

	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.PATCH)
	public TeamResponse updateTeam(@PathVariable("teamId") final Integer teamId, @RequestBody TeamRequest teamRequest) {
		return new TeamResponse(teamService.updateUserById(teamId, teamRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.DELETE)
	public void deleteTeam(@PathVariable("teamId") final Integer teamId) {
		teamService.deleteTeamById(teamId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/teams/{teamId}/organization/{organizationId}", method = RequestMethod.POST)
	public void setOrganization(@PathVariable("teamId") final Integer teamId, @PathVariable("organizationId") final Integer organizationId) {
		teamService.setOrganizationForTeam(teamId, organizationId);
	}
	
	@RequestMapping(value = "/teams/{teamId}/users", method = RequestMethod.GET)
	public List<Integer> getUsersOfTeam(@PathVariable("teamId") final Integer teamId) {
		return teamService.getUsers(teamId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/teams/{teamId}/users/{userId}", method = RequestMethod.POST)
	public void addUserToTeam(@PathVariable("teamId") final Integer teamId, @PathVariable("userId") final Integer userId) {
		teamService.addUser(teamId, userId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/teams/{teamId}/users/{userId}", method = RequestMethod.DELETE)
	public void removeUserFromTeam(@PathVariable("teamId") final Integer teamId, @PathVariable("userId") final Integer userId) {
		teamService.removeUser(teamId, userId);
	}

}
