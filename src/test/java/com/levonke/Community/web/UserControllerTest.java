package com.levonke.Community.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levonke.Community.domain.User;
import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.service.UserServiceImpl;
import com.levonke.Community.web.model.UserRequest;
import com.levonke.Community.web.model.UserResponse;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import java.util.ArrayList;
import java.util.Optional;

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
	@DisplayName("Create user")
	void createUser() throws Exception {
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
		
		MvcResult result = this.mockMvc.perform(
				post(UserController.userBaseURI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userRequest))
				).andExpect(status().isCreated())
				.andExpect(header().string("Location", "/api/community/users/1"))
				.andReturn();
		UserResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<UserResponse>() {
		});
		
		verify(userRepositoryMock, times(1)).save(userNoId);
		assertEquals("Invalid country response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Get user")
	void getUser() throws Exception {
		Optional<User> userOptional = Optional.of(user);
		
		when(userRepositoryMock.findById(1)).thenReturn(userOptional);
		
		MvcResult result = this.mockMvc.perform(
			get(UserController.userBaseURI + "/1"))
			.andExpect(status().is2xxSuccessful()
			).andReturn();
		
		UserResponse expectedResponse = new UserResponse(user);
		UserResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<UserResponse>() { });
		
		verify(userRepositoryMock, times(1)).findById(1);
		assertEquals("Invalid country response", expectedResponse, actualResponse);
	}
	
	// TODO: implement
	@Disabled
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
				patch(UserController.userBaseURI + "/1")
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
		this.mockMvc.perform(
			delete(UserController.userBaseURI + "/1")
			).andExpect(status().isNoContent());
		
		verify(userRepositoryMock, times(1)).deleteById(1);
	}

}
