package com.levonke.Community.repository;

import com.levonke.Community.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
		extends JpaRepository<User, Integer> {
	
	User getUserByUsernameIgnoreCase(String username);
	
	User getUserByRegEmailIgnoreCase(String regEmail);
	
	Page<User> getUsersByUsernameContainingIgnoreCase(String username, Pageable pageable);
	
}
