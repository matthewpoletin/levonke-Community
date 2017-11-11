package com.levonke.Community.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "teams", schema = "community")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teams_id")
	private Integer id;
	
	@Column(name = "teams_name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teams_organization_id")
	private Organization organization;
	
	@ManyToMany(fetch = FetchType.EAGER/*cascade = {CascadeType.PERSIST, CascadeType.MERGE}*/)
	@JoinTable(
			name = "teams_users",
			joinColumns = @JoinColumn(name = "teams_teams_id", referencedColumnName = "teams_id"),
			inverseJoinColumns = @JoinColumn(name = "users_users_id", referencedColumnName = "users_id"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"teams_teams_id", "users_users_id"})
	)
	private Collection<User> users = new HashSet<>();
	
}
