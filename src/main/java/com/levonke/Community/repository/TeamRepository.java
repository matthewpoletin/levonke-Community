package com.levonke.Community.repository;

import com.levonke.Community.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository
		extends JpaRepository<Team, Integer> {
}
