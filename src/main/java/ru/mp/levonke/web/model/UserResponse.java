package ru.mp.levonke.web.model;

import lombok.Data;
//import lombok.Getter;
import lombok.NoArgsConstructor;

import ru.mp.levonke.domain.User;

@Data
@NoArgsConstructor
public class UserResponse {
	private String username;
	private String firstname;
	private String surname;
	private String regEmail;
	private String pubEmail;
	private String ghLink;
	private String fbLink;

	public UserResponse(User user) {
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.surname = user.getSurname();
		this.regEmail = user.getRegEmail();
		this.pubEmail = user.getPubEmail();
		this.ghLink = user.getGhLink();
		this.fbLink = user.getFbLink();
	}
}
