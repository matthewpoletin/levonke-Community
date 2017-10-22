package ru.mp.levonke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@Column(name = "users_username", unique = true)
	private String username;

	@Column(name = "users_firstname")
	private String firstname;

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

	@JsonIgnore
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Collection<Organization> organizations = new ArrayList<Organization>();

}
