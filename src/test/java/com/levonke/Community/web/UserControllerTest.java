package com.levonke.Community.web;

import com.levonke.Community.domain.User;
import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.service.UserServiceImpl;
import com.levonke.Community.web.model.UserRequest;
import com.levonke.Community.web.model.UserResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@DisplayName("UserController Test")
class UserControllerTest {
	
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
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@BeforeAll
	void setUpMockMvc(WebApplicationContext wac) {
		mockMvc = webAppContextSetup(wac)
			.build();
	}
	
	@Test
	@DisplayName("Get users")
	void getUsers() throws Exception {
		// Arrange
		List<User> users= new ArrayList<User>() {{
			add(user);
		}};
		
		when(userRepositoryMock.findAll()).thenReturn(users);
		
		List<UserResponse> expectedResponse = users
				.stream()
				.map(UserResponse::new)
				.collect(Collectors.toList());
		
		// Act
		MvcResult result = this.mockMvc.perform(
				get(UserController.userBaseURI + "/users"/* + "?page=0&size=25"*/).param("page", "0").param("size", "25"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].id").exists())
				.andReturn();
		
		// Assert
		UserResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<UserResponse>>() { });
		assertEquals("Invalid car response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Create user")
	void createUser() throws Exception {
		// Arrange
		User userNoId = new User()
				.setUsername("Username")
				.setPassword("Password")
				.setForename("Forename")
				.setSurname("Surname")
				.setRegEmail("name@server.domain")
				.setPubEmail("name@server.domain")
				.setFbLink("fb.com/username")
				.setGhLink("github.com/username")
				.setOrganizations(new ArrayList<>())
				.setTeams(new ArrayList<>());
		
		when(userRepositoryMock.save(userNoId)).thenReturn(user);
		
		UserRequest userRequest = new UserRequest()
				.setUsername("Username")
				.setPassword("Password")
				.setForename("Forename")
				.setSurname("Surname")
				.setRegEmail("name@server.domain")
				.setPubEmail("name@server.domain")
				.setFbLink("fb.com/username")
				.setGhLink("github.com/username");
		
		UserResponse expectedResponse = new UserResponse(user);
		
		// Act
		MvcResult result = this.mockMvc.perform(
				post(UserController.userBaseURI + "/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userRequest))
				).andExpect(status().isCreated())
				.andExpect(header().string("Location", UserController.userBaseURI + "/users" + "/1"))
				.andReturn();
		UserResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<UserResponse>() { });
		
		// Assert
		verify(userRepositoryMock, times(1)).save(userNoId);
		assertEquals("Invalid country response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Get user")
	void getUser() throws Exception {
		// Arrange
		Optional<User> userOptional = Optional.of(user);
		
		when(userRepositoryMock.findById(1)).thenReturn(userOptional);
		
		// Act
		MvcResult result = this.mockMvc.perform(
			get(UserController.userBaseURI + "/users" + "/1"))
			.andExpect(status().is2xxSuccessful()
			).andReturn();
		
		UserResponse expectedResponse = new UserResponse(user);
		UserResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<UserResponse>() { });
		
		// Accert
		verify(userRepositoryMock, times(1)).findById(1);
		assertEquals("Invalid country response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Update user")
	void updateUser() throws Exception {
		// Arrange
		UserRequest userRequest = new UserRequest()
			.setForename("Forename")
			.setSurname("Surname")
			.setPassword("Password")
			.setFbLink("fb.me/nickname");
		
		// Act
		this.mockMvc.perform(
				patch(UserController.userBaseURI + "/users" + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userRequest)))
				.andExpect(status().isOk());
		
		// Assert
		verify(userService, times(1))
			.updateUserById(1, userRequest);
	}
	
	@Test
	@DisplayName("Delete user")
	void deleteUser() throws Exception {
		// Act
		this.mockMvc.perform(
			delete(UserController.userBaseURI + "/users" + "/1")
			).andExpect(status().isNoContent());
		
		// Assert
		verify(userRepositoryMock, times(1)).deleteById(1);
	}

}
