package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.UserRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
	Page<User> getUsers(Integer page, Integer size);
	Page<User> getUsersWithUsername(String username, Integer page, Integer size);
	User createUser(UserRequest	userRequest);
	User getUserById(Integer userId);
	User getUserByUsername(String username);
	User getUserByRegEmail(String regEmail);
	User updateUserById(Integer userId, UserRequest userRequest);
	void deleteUserById(Integer userId);
	
	List<Integer> getTeamsOfUser(Integer userId);
	
}
