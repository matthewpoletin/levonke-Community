package com.levonke.Community.web.model;

import lombok.Data;

@Data
public class UserRequest {
	private String username;
	private String firstname;
	private String surname;
	private String regEmail;
	private String pubEmail;
	private String ghLink;
	private String fbLink;
}
