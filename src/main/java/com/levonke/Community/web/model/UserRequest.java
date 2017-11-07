package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequest {
	private String username;
	private String forename;
	private String surname;
	private String regEmail;
	private String pubEmail;
	private String ghLink;
	private String fbLink;
}
