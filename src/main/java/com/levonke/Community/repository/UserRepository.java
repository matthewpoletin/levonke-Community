package com.levonke.Community.repository;

import com.levonke.Community.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
		extends CrudRepository<User, Integer> {
}
