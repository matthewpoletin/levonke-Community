package com.levonke.Community.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users", schema = "community")
public class User {
	
	@Id
	@Column(name = "users_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// TODO: Make unique case-insensitive
	@Column(name = "users_username", unique = true)
	private String username;
	
	@Column(name = "users_password")
	private String password;
	
	@Column(name = "users_forename")
	private String forename;
	
	@Column(name = "users_surname")
	private String surname;
	
	@Column(name = "users_regemail")
	private String regEmail;
	
	@Column(name = "users_pubemail")
	private String pubEmail;
	
	@Column(name = "users_ghlink")
	private String ghLink;
	
	@Column(name = "users_fblink")
	private String fbLink;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Collection<Organization> organizations = new ArrayList<>();
	
	@ManyToMany(mappedBy = "users", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Collection<Team> teams = new HashSet<>();
	
}
