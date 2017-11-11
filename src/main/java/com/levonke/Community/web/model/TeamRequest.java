package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamRequest {
	private Integer id;
	private String name;
	private Integer organizationId;
}
