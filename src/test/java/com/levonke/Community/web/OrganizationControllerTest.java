package com.levonke.Community.web;

import com.levonke.Community.Application;
import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.User;
import com.levonke.Community.repository.OrganizationRepository;
import com.levonke.Community.service.UserService;
import com.levonke.Community.web.model.OrganizationResponse;

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
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(/*classes = Application.class, */webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("OrganizationController Test")
public class OrganizationControllerTest {
	
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
	
	private final Organization organization = new Organization()
		.setId(1)
		.setName("Name")
		.setDescription("Description")
		.setPubEmail("Public Email")
		.setWebsite("website")
		.setOwner(user);
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private OrganizationRepository organizationRepositoryMock;
	
	@Test
	@DisplayName("getOrganization")
	void getOrganization() {
		Optional<Organization> organizationOptional = Optional.of(organization);

		when(organizationRepositoryMock.findById(1)).thenReturn(organizationOptional);

		OrganizationResponse expectedResponse = new OrganizationResponse(organization);
		OrganizationResponse actualResponse = restTemplate.getForObject("/api/community/organizations/1", OrganizationResponse.class);

		verify(organizationRepositoryMock, times(1)).findById(1);
		assertThat("Should return valid organization", actualResponse, equalTo(expectedResponse));
	}
	
}
