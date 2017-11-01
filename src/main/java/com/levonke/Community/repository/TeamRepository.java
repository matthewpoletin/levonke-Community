package com.levonke.Community.repository;

import com.levonke.Community.domain.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository
		extends CrudRepository<Team, Integer> {
}
