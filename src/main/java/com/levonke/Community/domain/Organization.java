package com.levonke.Community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "organizations", schema = "community")
public class Organization {
	
	@Id
	@Column(name = "organizations_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "organizations_name", unique = true)
	private String name;
	
	@Column(name = "organizations_official_name")
	private String officialName;
	
	@Column(name = "organizations_description")
	private String description;
	
	@Column(name = "organizations_pubemail")
	private String pubEmail;
	
	@Column(name = "organizations_website")
	private String website;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizations_owner_id")
	private User owner;
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
	private Collection<Team> teams = new ArrayList<>();
	
}
