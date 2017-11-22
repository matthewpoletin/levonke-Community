package com.levonke.Community.web;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.domain.User;
import com.levonke.Community.repository.OrganizationRepository;
import com.levonke.Community.service.OrganizationServiceImpl;
import com.levonke.Community.service.UserService;
import com.levonke.Community.web.model.OrganizationRequest;
import com.levonke.Community.web.model.OrganizationResponse;

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
@DisplayName("OrganizationController Test")
class OrganizationControllerTest {
	
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
		.setWebsite("server.com");
//		.setOwner(user);
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private OrganizationServiceImpl organizationService;
	
	@MockBean
	private OrganizationRepository organizationRepositoryMock;
	
//	@MockBean
//	private UserService userServiceMock;
	
	@BeforeAll
	void setUpMockMvc(WebApplicationContext wac) {
		mockMvc = webAppContextSetup(wac)
			.build();
	}
	
	@Test
	@DisplayName("Get organizations")
	void getOrganizations() throws Exception {
		// Arrange
		List<Organization> organizations= new ArrayList<Organization>() {{
			add(organization);
		}};
		
		PageRequest pr = PageRequest.of(0, 25);
		PageImpl<Organization> clientPage = new PageImpl<>(organizations, pr, 100);
		
		when(organizationRepositoryMock.findAll(any(Pageable.class))).thenReturn(clientPage);
		
		List<OrganizationResponse> expectedResponse = organizations
				.stream()
				.map(OrganizationResponse::new)
				.collect(Collectors.toList());
		
		// Act
		MvcResult result = this.mockMvc.perform(
			get(OrganizationController.organizationBaseURI + "/organizations")
				.param("page", "0")
				.param("size", "25")
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$[0].id").exists())
			.andReturn();
		
		List<OrganizationResponse> actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<OrganizationResponse>>() { });
		
		// Assert
		assertEquals("Invalid organization response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Create organization")
	void createOrganization() throws Exception {
		// Arrange
		Organization organizationNoId = new Organization()
			.setName("Name")
			.setDescription("Description")
			.setPubEmail("name@server.domain")
			.setWebsite("server.com");
//			.setOwner(user);
		
		when(organizationRepositoryMock.save(organizationNoId)).thenReturn(organization);
		
		OrganizationRequest organizationRequest = new OrganizationRequest()
			.setName("Name")
			.setDescription("Description")
			.setPubEmail("name@server.domain")
			.setWebsite("server.com");
//			.setOwner(user);
		
		OrganizationResponse expectedResponse = new OrganizationResponse(organization);
		
		// Act
		MvcResult result = this.mockMvc.perform(
			post(OrganizationController.organizationBaseURI + "/organizations")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(organizationRequest))
			)
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", OrganizationController.organizationBaseURI + "/organizations" + "/1"))
			.andReturn();
		OrganizationResponse actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<OrganizationResponse>() { });
		
		// Assert
		verify(organizationRepositoryMock, times(1)).save(organizationNoId);
		assertEquals("Invalid country response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Get organization")
	void getOrganization() throws Exception {
		// Arrange
		Optional<Organization> organizationOptional = Optional.of(organization);
		
		when(organizationRepositoryMock.findById(1)).thenReturn(organizationOptional);
		
		// Act
		MvcResult result = this.mockMvc.perform(
				get(OrganizationController.organizationBaseURI + "/organizations" + "/1")
			)
			.andExpect(status().is2xxSuccessful())
			.andReturn();
		
		OrganizationResponse expectedResponse = new OrganizationResponse(organization);
		OrganizationResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<OrganizationResponse>() { });
		
		// Assert
		verify(organizationRepositoryMock, times(1)).findById(1);
		assertEquals("Invalid country response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Update organization")
	void updateOrganization() throws Exception {
		// Arrange
		Organization organizationUpdated = new Organization()
			.setId(1)
			.setName("NAME")
			.setDescription("Description")
			.setPubEmail("name@server.domain")
			.setWebsite("server.com");
//			.setOwner(user);
		
		Optional<Organization> organizationOptional = Optional.of(organization);
		
		when(organizationRepositoryMock.findById(1)).thenReturn(organizationOptional);
		when(organizationRepositoryMock.save(any(Organization.class))).thenReturn(organizationUpdated);
		
		OrganizationRequest organizationRequest = new OrganizationRequest()
			.setName("NAME");
		
		// Act
		MvcResult result = this.mockMvc.perform(
			patch(OrganizationController.organizationBaseURI + "/organizations" + "/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(organizationRequest))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id").exists())
			.andReturn();
		
		OrganizationResponse expectedResponse = new OrganizationResponse(organization);
		expectedResponse.setName("NAME");
		OrganizationResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<OrganizationResponse>() { });
		
		// Assert
		verify(organizationRepositoryMock, times(1)).findById(1);
		verify(organizationRepositoryMock, times(1)).save(organizationUpdated);
		assertEquals("Invalid organization response", expectedResponse, actualResponse);
	}
	
	@Test
	@DisplayName("Delete organization")
	void deleteOrganization() throws Exception {
		// Act
		this.mockMvc.perform(
			delete(OrganizationController.organizationBaseURI + "/organizations" + "/1")
			)
			.andExpect(status().isNoContent());
		
		// Assert
		verify(organizationRepositoryMock, times(1)).deleteById(1);
	}
	
}
