package com.levonke.Community.web;

import com.levonke.Community.domain.User;

import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.web.model.UserResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("UserController Test")
public class UserControllerTest {

	private final User user = new User()
			.setId(1)
			.setUsername("Username")
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
	
//	@Test
//	@DisplayName("createAccount should check client/currency and add it to account")
//	void createAccount() {
//		UserRequest userRequest = new UserRequest()
//				.setUsername("Username")
//				.setForename("Forename")
//				.setSurname("Surname")
//				.setRegEmail("name@server.domain")
//				.setPubEmail("name@server.domain")
//				.setFbLink("fb.com/username")
//				.setGhLink("github.com/username");
		
//		Account accountFromRequest = new Account()
//				.setClient(client)
//				.setCurrency(currency)
//				.setBalance(new BigDecimal(0));


//		when(organizationServiceMock.getById(1)).thenReturn(client);
//		when(currencyServiceMock.getByKey("RUB")).thenReturn(currency);
//		when(accountRepositoryMock.save(accountFromRequest)).thenReturn(account);
//
//		AccountResponse expectedResponse = new AccountResponse(account);
//		AccountResponse actualResponse = restTemplate.postForObject("/api/account/account",
//				accountRequest, AccountResponse.class);
//
//		verify(clientServiceMock, times(1)).getById(1);
//		verify(currencyServiceMock, times(1)).getByKey("RUB");
//		verify(clientServiceMock, times(1)).addAccount(1, account);
//		verify(accountRepositoryMock, times(1)).save(accountFromRequest);
//		assertEquals("Invalid account response", expectedResponse, actualResponse);
//	}
	
//	@Test
//	@DisplayName("getUser should return valid user")
//	void getUser() {
//		Optional<User> userOptional = Optional.of(user);
//
//		when(userRepositoryMock.findById(1)).thenReturn(userOptional);
//
//		UserResponse expectedResponse = new UserResponse(user);
//		UserResponse actualResponse = restTemplate.getForObject("/api/account/account/1", UserResponse.class);
//
//		verify(userRepositoryMock, times(1)).findById(1);
//		assertEquals("Invalid account response", expectedResponse, actualResponse);
//	}
	
}
