package com.levonke.Community.web;

import com.levonke.Community.domain.User;

import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.web.model.UserResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("UserController Test")
public class UserControllerTest {
	
	private final User user = new User()
		.setId(1)
		.setUsername("Username")
		.setPassword("Password")
		.setForename("Forename")
		.setRegEmail("name@server.domain")
		.setSurname("Surname")
		.setPubEmail("name@server.domain")
		.setFbLink("fb.com/username")
		.setGhLink("github.com/username");
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@Test
	@DisplayName("getUser")
	void getUser() {
		Optional<User> userOptional = Optional.of(user);
		
		when(userRepositoryMock.findById(1)).thenReturn(userOptional);
		
		UserResponse expectedResponse = new UserResponse(user);
		UserResponse actualResponse = restTemplate.getForObject("/api/community/users/1", UserResponse.class);
		
		verify(userRepositoryMock, times(1)).findById(1);
		assertThat("Should return valid user", actualResponse, equalTo(expectedResponse));
	}

}
