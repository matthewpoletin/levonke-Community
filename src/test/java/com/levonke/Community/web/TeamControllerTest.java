package com.levonke.Community.web;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.Team;
import com.levonke.Community.domain.User;
import com.levonke.Community.repository.TeamRepository;
import com.levonke.Community.service.OrganizationService;
import com.levonke.Community.service.TeamServiceImpl;
import com.levonke.Community.service.UserService;
import com.levonke.Community.service.UserServiceImpl;
import com.levonke.Community.web.model.TeamRequest;
import com.levonke.Community.web.model.TeamResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("TeamController Test")
class TeamControllerTest {
	
	private final User user = new User()
		.setUsername("Username")
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
//		.setTeams(ArrayList<Team>().add(team))
	
	private final Team team = new Team()
		.setId(1)
		.setName("Name");
//		.setOrganization(organization);
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private TeamServiceImpl teamService;
	
	@MockBean
	private TeamRepository teamRepositoryMock;
	
//	@MockBean
//	private UserService userServiceMock;
//
//	@MockBean
//	private OrganizationService organizationServiceMock;
	
	@BeforeAll
	void setUpMockMvc(WebApplicationContext wac) {
		mockMvc = webAppContextSetup(wac)
			.build();
	}
	
	@Test
	@DisplayName("Get teams")
	void getTeams() throws Exception {
		// Arrange
		List<Team> teams= new ArrayList<Team>() {{
			add(team);
		}};
		
		PageRequest pr = PageRequest.of(0, 25);
		PageImpl<Team> teamPage = new PageImpl<>(teams, pr, 100);
		
		when(teamRepositoryMock.findAll(any(Pageable.class))).thenReturn(teamPage);
		
		List<TeamResponse> expectedResponse = teams
			.stream()
			.map(TeamResponse::new)
			.collect(Collectors.toList());
		
		// Act
		MvcResult result = this.mockMvc.perform(
			get(TeamController.teamBaseURI + "/teams")
				.param("page", "0")
				.param("size", "25")
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].id").exists())
			.andReturn();
	
		List<TeamResponse> actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<TeamResponse>>() { });
		
		// Assert
		assertEquals("Invalid team response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Create team")
	void createTeam() throws Exception {
		// Arrange
		Team teamNoId = new Team()
			.setName("Name");

		when(teamRepositoryMock.save(teamNoId)).thenReturn(team);
		
		TeamRequest teamRequest = new TeamRequest()
			.setName("Name");
		
		TeamResponse expectedResponse = new TeamResponse(team);
		
		// Act
		MvcResult result = this.mockMvc.perform(
			post(TeamController.teamBaseURI + "/teams")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(teamRequest))
			)
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", TeamController.teamBaseURI + "/teams" + "/1"))
			.andReturn();
		TeamResponse actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<TeamResponse>() { });
		
		// Assert
		verify(teamRepositoryMock, times(1)).save(teamNoId);
		assertEquals("Invalid team response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Get team")
	void getTeam() throws Exception {
		// Arrange
		Optional<Team> teamOptional = Optional.of(team);
		
		when(teamRepositoryMock.findById(1)).thenReturn(teamOptional);
		
		// Act
		MvcResult result = this.mockMvc.perform(
				get(TeamController.teamBaseURI + "/teams" + "/1")
			)
			.andExpect(status().is2xxSuccessful())
			.andReturn();
		
		TeamResponse expectedResponse = new TeamResponse(team);
		TeamResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<TeamResponse>() { });
		
		// Assert
		verify(teamRepositoryMock, times(1)).findById(1);
		assertEquals("Invalid team response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Update team")
	void updateTeam() throws Exception {
		// Arrange
		Team teamUpdated = new Team()
				.setId(1)
				.setName("NAME");
		
		Optional<Team> teamOptional = Optional.of(team);
		
		when(teamRepositoryMock.findById(1)).thenReturn(teamOptional);
		when(teamRepositoryMock.save(any(Team.class))).thenReturn(teamUpdated);
		
		TeamRequest teamRequest = new TeamRequest()
				.setName("NAME");
		
		// Act
		MvcResult result = this.mockMvc.perform(
			patch(TeamController.teamBaseURI + "/teams" + "/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(teamRequest))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id").exists())
			.andReturn();
		
		TeamResponse expectedResponse = new TeamResponse(team);
		expectedResponse.setName("NAME");
		TeamResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<TeamResponse>() { });
		
		// Assert
		verify(teamRepositoryMock, times(1)).findById(1);
		verify(teamRepositoryMock, times(1)).save(teamUpdated);
		assertEquals("Invalid team response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Delete team")
	void deleteTeam() throws Exception {
		// Act
		this.mockMvc.perform(
				delete(TeamController.teamBaseURI + "/teams" + "/1")
			)
			.andExpect(status().isNoContent());
		
		// Assert
		verify(teamRepositoryMock, times(1)).deleteById(1);
	}
	
}
