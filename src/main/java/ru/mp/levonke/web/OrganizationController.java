package ru.mp.levonke.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import ru.mp.levonke.domain.Organization;
import ru.mp.levonke.service.OrganizationServiceImpl;
import ru.mp.levonke.web.model.OrganizationRequest;
import ru.mp.levonke.web.model.OrganizationResponse;

@RestController
@RequestMapping(OrganizationController.ORGANIZATION_BASE_URI)
public class OrganizationController {
	public static final String ORGANIZATION_BASE_URI = "/organizations";

	@Autowired
	OrganizationServiceImpl organizationService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<OrganizationResponse> getOrganizations() {
		ArrayList<OrganizationResponse> organizationResponses = new ArrayList<OrganizationResponse>();
		organizationService.getOrganizations().forEach(organization -> organizationResponses.add(new OrganizationResponse(organization)));
		return organizationResponses;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public  void createOrganization(@RequestBody OrganizationRequest organizationRequest, HttpServletResponse response) {
		Organization organization = organizationService.create(organizationRequest);
		response.addHeader(HttpHeaders.LOCATION, this.ORGANIZATION_BASE_URI + "/" + organization.getId());
	}

	@RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
	public OrganizationResponse getOrganization(@PathVariable("organizationId") final Integer organizationId) {
		return new OrganizationResponse(organizationService.read(organizationId));
	}

	@RequestMapping(value = "/{organizationId}", method = RequestMethod.PATCH)
	public OrganizationResponse update(@PathVariable("organizationId") final Integer organizationId, @RequestBody OrganizationRequest organizationRequest) {
		return new OrganizationResponse(organizationService.update(organizationId, organizationRequest));
	}

	@RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
	public void deleteOrganization(@PathVariable("organizationId") final Integer organizationId) {
		organizationService.delete(organizationId);
	}

}
