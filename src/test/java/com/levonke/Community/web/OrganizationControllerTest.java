package com.levonke.Community.web;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.User;
import com.levonke.Community.repository.OrganizationRepository;
import com.levonke.Community.service.OrganizationServiceImpl;
import com.levonke.Community.web.model.OrganizationResponse;

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
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("OrganizationController Test")
public class OrganizationControllerTest {
	
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
		.setPubEmail("name@server.domain")
		.setWebsite("example.org")
		.setOwner(user);
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@InjectMocks
	private OrganizationServiceImpl organizationService;
	
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
