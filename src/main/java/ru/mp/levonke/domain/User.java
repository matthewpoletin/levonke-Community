package ru.mp.levonke.domain;

import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String name) {
		this.firstname = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRegEmail() {
		return regEmail;
	}

	public void setRegEmail(String regEmail) {
		this.regEmail = regEmail;
	}

	public String getPubEmail() {
		return pubEmail;
	}

	public void setPubEmail(String pubEmail) {
		this.pubEmail = pubEmail;
	}

	public String getGhLink() {
		return ghLink;
	}

	public void setGhLink(String ghLink) {
		this.ghLink = ghLink;
	}

	public String getFbLink() {
		return fbLink;
	}

	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}

}
