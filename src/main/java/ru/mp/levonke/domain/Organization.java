package ru.mp.levonke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "organizations", schema = "community")
public class Organization {

	@Id
	@Column(name = "organizations_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "organizations_name")
	private String name;

	@Column(name = "organizations_description")
	private String description;

	@Column(name = "organizations_pubemail")
	private String pubEmail;

	@Column(name = "organizations_website")
	private String website;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organizations_owner_id")
	private User owner;

}
