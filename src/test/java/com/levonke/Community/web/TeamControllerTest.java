package com.levonke.Community.web;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.Team;
import com.levonke.Community.domain.User;
import com.levonke.Community.repository.TeamRepository;
import com.levonke.Community.service.OrganizationService;
import com.levonke.Community.service.TeamServiceImpl;
import com.levonke.Community.service.UserService;
import com.levonke.Community.web.model.TeamResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("TeamController Test")
public class TeamControllerTest {
	
	private final User user = new User()
		.setId(1)
		.setUsername("Username")
		.setPassword("Password")
		.setForename("Forename")
		.setSurname("Surname")
		.setRegEmail("name@server.domain")
		.setPubEmail("name@server.domain")
		.setFbLink("fb.com/username")
		.setGhLink("github.com/username");
	
	private final Organization organization = new Organization()
		.setId(1)
		.setName("Name")
		.setDescription("Description")
		.setPubEmail("name@server.com")
		.setWebsite("server.com")
		.setOwner(user);
	
	private final Team team = new Team()
		.setId(1)
		.setUsers(new ArrayList<>())
		.setOrganization(organization)
		.setName("Name");
	
	@Autowired
	private TestRestTemplate restTemplate;

	@InjectMocks
	private TeamServiceImpl teamService;
	
	@MockBean
	private TeamRepository teamRepositoryMock;
	
	@MockBean
	private UserService userServiceMock;
	
	@MockBean
	private OrganizationService organizationServiceMock;
	
	@Test
	@DisplayName("createTeam")
	void createTeam() {
	
	}
	
	@Test
	@DisplayName("getTeam")
	void getTeam() {
		Optional<Team> teamOptional = Optional.of(team);
		
		when(teamRepositoryMock.findById(1)).thenReturn(teamOptional);
		
		TeamResponse expectedResponse = new TeamResponse(team);
		TeamResponse actualResponse = restTemplate.getForObject("/api/community/teams/1", TeamResponse.class);
		
		verify(teamRepositoryMock, times(1)).findById(1);
		assertThat("Should return valid team", actualResponse, equalTo(expectedResponse));
	}
	
	@Test
	@DisplayName("updateTeam")
	void updateTeam() {
	
	}
	
	@Test
	@DisplayName("deleteTeam")
	void deleteTeam() {
		restTemplate.delete("/api/community/teams/1");
		
		verify(teamRepositoryMock, times(1)).deleteById(1);
	}
	
}
