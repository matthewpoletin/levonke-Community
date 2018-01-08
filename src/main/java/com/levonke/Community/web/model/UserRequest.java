package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class UserRequest {
	
	@NotEmpty(message = "Not valid username")
	private String username;
	
	@NotEmpty(message = "Not valid regEmail")
	private String regEmail;
	
	private String avatar;
	
	private String bio;
	
	private String forename;
	
	private String surname;
	
	private String pubEmail;
	
	private String ghLink;
	
	private String fbLink;
	
}
