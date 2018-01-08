package com.levonke.Community.web.model;

import com.levonke.Community.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
	private Integer id;
	private String username;
	private String avatar;
	private String bio;
	private String forename;
	private String surname;
	private String regEmail;
	private String pubEmail;
	private String ghLink;
	private String fbLink;

	public UserResponse(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.avatar = user.getAvatar();
		this.bio = user.getBio();
		this.forename = user.getForename();
		this.surname = user.getSurname();
		this.regEmail = user.getRegEmail();
		this.pubEmail = user.getPubEmail();
		this.ghLink = user.getGhLink();
		this.fbLink = user.getFbLink();
	}
}
