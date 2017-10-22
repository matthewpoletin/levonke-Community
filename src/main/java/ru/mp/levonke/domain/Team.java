package ru.mp.levonke.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teams_orgnaization_id")
	private Organization organization;

	@ManyToMany(mappedBy = "teams")
	private Collection<User> users = new ArrayList<User>();

}
