package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class TeamRequest {
	
	@NotEmpty(message = "Not valid name")
	private String name;
	
	private Integer organizationId;
	
}
