package ru.mp.levonke.repository;

import org.springframework.data.repository.CrudRepository;

import ru.mp.levonke.domain.User;

public interface UserRepository
		extends CrudRepository<User, Integer> {
}
