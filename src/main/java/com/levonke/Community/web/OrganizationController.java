package com.levonke.Community.web;

import com.levonke.Community.domain.Organization;
import com.levonke.Community.service.OrganizationServiceImpl;
import com.levonke.Community.web.model.OrganizationRequest;
import com.levonke.Community.web.model.OrganizationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(OrganizationController.organizationBaseURI)
public class OrganizationController {
	
	static final String organizationBaseURI = "/api/community/organizations";
	
	private OrganizationServiceImpl organizationService;
	
	@Autowired
	OrganizationController(OrganizationServiceImpl organizationService) {
		this.organizationService = organizationService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<OrganizationResponse> getOrganizations(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		return organizationService.getOrganizations(page, size)
			.stream()
			.map(OrganizationResponse::new)
			.collect(Collectors.toList());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public OrganizationResponse createOrganization(@RequestBody OrganizationRequest organizationRequest, HttpServletResponse response) {
		Organization organization = organizationService.createOrganization(organizationRequest);
		response.addHeader(HttpHeaders.LOCATION, organizationBaseURI + "/" + organization.getId());
		return new OrganizationResponse(organization);
	}
	
	@RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
	public OrganizationResponse getOrganization(@PathVariable("organizationId") final Integer organizationId) {
		return new OrganizationResponse(organizationService.getOrganizationById(organizationId));
	}
	
	@RequestMapping(value = "/{organizationId}", method = RequestMethod.PATCH)
	public OrganizationResponse update(@PathVariable("organizationId") final Integer organizationId, @RequestBody OrganizationRequest organizationRequest) {
		return new OrganizationResponse(organizationService.updateOrganizationById(organizationId, organizationRequest));
	}
	
	@RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
	public void deleteOrganization(@PathVariable("organizationId") final Integer organizationId) {
		organizationService.deleteOrganizationById(organizationId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/{organizationId}/owner/{userId}", method = RequestMethod.POST)
	public void setOwnerToOrganization(@PathVariable("organizationId") final Integer organizationId, @PathVariable("userId") final Integer userId) {
		organizationService.setOwnerToOrganization(organizationId, userId);
	}
	
}
