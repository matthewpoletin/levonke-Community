package com.levonke.Community.web;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.service.OrganizationServiceImpl;
import com.levonke.Community.web.model.OrganizationRequest;
import com.levonke.Community.web.model.OrganizationResponse;

import com.levonke.Community.web.model.TeamResponse;
import com.levonke.Community.web.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(OrganizationController.organizationBaseURI)
public class OrganizationController {
	
	static final String organizationBaseURI = "/api/community";
	
	private OrganizationServiceImpl organizationService;
	
	@Autowired
	OrganizationController(OrganizationServiceImpl organizationService) {
		this.organizationService = organizationService;
	}
	
	@RequestMapping(value = "/organizations", method = RequestMethod.GET)
	public List<OrganizationResponse> getOrganizations(@RequestParam(value = "page", required = false) Integer page,
													   @RequestParam(value = "size", required = false) Integer size,
													   @RequestParam(value = "name", required = false) String name) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		Page<Organization> organizationPage;
		if (name != null) organizationPage = organizationService.getOrganizationsWithName(name, page, size);
		else organizationPage = organizationService.getOrganizations(page, size);
		return organizationPage
			.stream()
			.map(OrganizationResponse::new)
			.collect(Collectors.toList());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/organizations", method = RequestMethod.POST)
	public OrganizationResponse createOrganization(@Valid @RequestBody OrganizationRequest organizationRequest, HttpServletResponse response) {
		Organization organization = organizationService.createOrganization(organizationRequest);
		response.addHeader(HttpHeaders.LOCATION, organizationBaseURI + "/organizations/" + organization.getId());
		return new OrganizationResponse(organization);
	}
	
	@RequestMapping(value = "/organizations/{organizationId}", method = RequestMethod.GET)
	public OrganizationResponse getOrganizationById(@PathVariable("organizationId") final Integer organizationId) {
		return new OrganizationResponse(organizationService.getOrganizationById(organizationId));
	}
	
	@RequestMapping(value = "/organizations/name/{name}", method = RequestMethod.GET)
	public OrganizationResponse getOrganizationByName(@PathVariable("name") final String name) {
		return new OrganizationResponse(organizationService.getOrganizationByName(name));
	}
	
	@RequestMapping(value = "/organizations/{organizationId}", method = RequestMethod.PATCH)
	public OrganizationResponse updateOrganizationById(@PathVariable("organizationId") final Integer organizationId, @Valid @RequestBody OrganizationRequest organizationRequest) {
		return new OrganizationResponse(organizationService.updateOrganizationById(organizationId, organizationRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/organizations/{organizationId}", method = RequestMethod.DELETE)
	public void deleteOrganizationById(@PathVariable("organizationId") final Integer organizationId) {
		organizationService.deleteOrganizationById(organizationId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/organizations/{organizationId}/owner/{userId}", method = RequestMethod.POST)
	public void setOwnerToOrganization(@PathVariable("organizationId") final Integer organizationId, @PathVariable("userId") final Integer userId) {
		organizationService.setOwnerToOrganization(organizationId, userId);
	}
	
	@RequestMapping(value = "/organizations/{organizationId}/owner", method = RequestMethod.GET)
	public UserResponse getOwnerOfOrganization(@PathVariable("organizationId") final Integer organizationId) {
		return new UserResponse(organizationService.getOwnerOfOrganization(organizationId));
	}
	
	@RequestMapping(value = "/organizations/{organizationId}/teams", method = RequestMethod.GET)
	public List<TeamResponse> getTeamsOfOrganization(@PathVariable("organizationId") final Integer organizationId,
													 @RequestParam(value = "page", required = false) Integer page,
													 @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		return organizationService.getTeamsOfOrganization(organizationId, page, size)
			.stream()
			.map(TeamResponse::new)
			.collect(Collectors.toList());
	}
	
}
