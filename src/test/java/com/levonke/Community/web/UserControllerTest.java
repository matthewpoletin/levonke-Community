package com.levonke.Community.web;

import com.levonke.Community.domain.User;
import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.service.UserServiceImpl;
import com.levonke.Community.web.model.UserRequest;
import com.levonke.Community.web.model.UserResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("UserController Test")
public class UserControllerTest {
	
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
	private TestRestTemplate restTemplate;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@Test
	@DisplayName("Create user")
	void createUser() {
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
		UserResponse actualResponse = restTemplate.postForObject("/api/community/users", userRequest, UserResponse.class);

		verify(userRepositoryMock, times(1)).save(userNoId);
		assertEquals("Invalid user response", expectedResponse, actualResponse);
//		assertThat("Should return valid user", actualResponse, equalTo(expectedResponse));
	}
	
	@Test
	@DisplayName("Get user")
	void getUser() {
		Optional<User> userOptional = Optional.of(user);

		when(userRepositoryMock.findById(1)).thenReturn(userOptional);

		UserResponse expectedResponse = new UserResponse(user);
		UserResponse actualResponse = restTemplate.getForObject("/api/community/users/1", UserResponse.class);

		verify(userRepositoryMock, times(1)).findById(1);
		assertThat("Should return valid user", actualResponse, equalTo(expectedResponse));
	}
	
	@Test
	@DisplayName("Update user")
	void updateUser() {
	
	}
	
	@Test
	@DisplayName("Delete user")
	void deleteUser() {
		restTemplate.delete("/api/community/users/1");
		
		verify(userRepositoryMock, times(1)).deleteById(1);
	}

}
