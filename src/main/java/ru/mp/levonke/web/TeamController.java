package ru.mp.levonke.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.mp.levonke.domain.Team;
import ru.mp.levonke.service.TeamServiceImpl;
import ru.mp.levonke.web.model.TeamRequest;
import ru.mp.levonke.web.model.TeamResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping(TeamController.TEAM_BASE_URI)
public class TeamController {

	public static final String TEAM_BASE_URI = "/teams";

	@Autowired
	TeamServiceImpl teamService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<TeamResponse> getTeams() {
		ArrayList<TeamResponse> teamResponses = new ArrayList<TeamResponse>();
		teamService.getTeams().forEach(team -> teamResponses.add(new TeamResponse(team)));
		return teamResponses;
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
