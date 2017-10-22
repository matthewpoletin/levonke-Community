package ru.mp.levonke.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mp.levonke.domain.Team;

public interface TeamRepository
		extends CrudRepository<Team, Integer> {
}
