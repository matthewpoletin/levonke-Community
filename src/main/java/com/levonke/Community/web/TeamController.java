package com.levonke.Community.web;

import com.levonke.Community.domain.Team;
import com.levonke.Community.service.TeamServiceImpl;
import com.levonke.Community.web.model.OrganizationResponse;
import com.levonke.Community.web.model.TeamRequest;
import com.levonke.Community.web.model.TeamResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
	public Page<TeamResponse> getTeams(@RequestParam(value = "page", required = false) Integer page,
									   @RequestParam(value = "size", required = false) Integer size,
									   @RequestParam(value = "name", required = false) String name) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		Page<Team> teamPage;
		if (name != null) teamPage = teamService.getTeamsWithName(name, page, size);
		else teamPage = teamService.getTeams(page, size);
		return teamPage
			.map(TeamResponse::new);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public TeamResponse createTeam(@Valid @RequestBody TeamRequest teamRequest,
								   HttpServletResponse response) {
		Team team = teamService.createTeam(teamRequest);
		response.addHeader(HttpHeaders.LOCATION, teamBaseURI + "/teams/" + team.getId());
		return new TeamResponse(team);
	}

	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.GET)
	public TeamResponse getTeamById(@PathVariable("teamId") final Integer teamId) {
		return new TeamResponse(teamService.getTeamById(teamId));
	}
	
	@RequestMapping(value = "/teams/by", method = RequestMethod.GET)
	public TeamResponse getTeamBy(@RequestParam(name = "name", required = false) final String name,
								  HttpServletResponse response) {
		Team team;
		if (name != null && name.length() > 0) {
			team = teamService.getTeamByName(name);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if (team != null) {
			return new TeamResponse(team);
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
	}
	
	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.PATCH)
	public TeamResponse updateTeam(@PathVariable("teamId") final Integer teamId,
								   @Valid @RequestBody TeamRequest teamRequest) {
		return new TeamResponse(teamService.updateUserById(teamId, teamRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.DELETE)
	public void deleteTeam(@PathVariable("teamId") final Integer teamId) {
		teamService.deleteTeamById(teamId);
	}
	
	@RequestMapping(value = "/teams/{teamId}/organization", method = RequestMethod.GET)
	public OrganizationResponse getOrganization(@PathVariable("teamId") final Integer teamId) {
		return new OrganizationResponse(teamService.getOrganizationOfTeam(teamId));
	}
	
	@RequestMapping(value = "/teams/{teamId}/users", method = RequestMethod.GET)
	public List<Integer> getUsersOfTeam(@PathVariable("teamId") final Integer teamId) {
		return teamService.getUsers(teamId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/teams/{teamId}/users/{userId}", method = RequestMethod.POST)
	public void addUserToTeam(@PathVariable("teamId") final Integer teamId,
							  @PathVariable("userId") final Integer userId) {
		teamService.addUser(teamId, userId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/teams/{teamId}/users/{userId}", method = RequestMethod.DELETE)
	public void removeUserFromTeam(@PathVariable("teamId") final Integer teamId,
								   @PathVariable("userId") final Integer userId) {
		teamService.removeUser(teamId, userId);
	}

}
