package ru.mp.levonke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users", schema = "community")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "surname")
	private String surname;

	@Column(name = "regemail")
	private String regEmail;

	@Column(name = "pubemail")
	private String pubEmail;

	@Column(name = "ghlink")
	private String ghLink;

	@Column(name = "fblink")
	private String fbLink;

	public User() {
	}

	public User(String username, String firstname, String surname, String regEmail, String pubEmail, String ghLink, String fbLink) {
		this.username = username;
		this.firstname = firstname;
		this.surname = surname;
		this.regEmail = regEmail;
		this.pubEmail = pubEmail;
		this.ghLink = ghLink;
		this.fbLink = fbLink;
	}

}
