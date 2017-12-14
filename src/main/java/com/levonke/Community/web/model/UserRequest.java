package com.levonke.Community.web.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class UserRequest {
	
	@NotEmpty(message = "Not valid username")
	private String username;
	
	@NotEmpty(message = "Not valid password")
	private String password;
	
	@NotEmpty(message = "Not valid regEmail")
	private String regEmail;
	
	private String forename;
	
	private String surname;
	
	private String pubEmail;
	
	private String ghLink;
	
	private String fbLink;
	
}
