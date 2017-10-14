package ru.mp.levonke.model;

public class User {

	private int id;
	private String name;
	private String surname;
	private String login;
	private String regEmail;
	private String pubEmail;
	private String ghLink;
	private String fbLink;

	public User() {
	}

	public User(int id, String name, String surname, String login, String regEmail, String pubEmail, String ghLink, String fbLink) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
