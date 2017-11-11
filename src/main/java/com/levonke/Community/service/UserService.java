package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.UserRequest;

import java.util.List;

public interface UserService {
	List<User> getUsers(Integer page, Integer count);
	User createUser(UserRequest	userRequest);
	User getUserById(Integer userId);
	User updateUserById(Integer userId, UserRequest userRequest);
	void deleteUserById(Integer userId);
	
	List<Integer> getTeamsOfUser(Integer userId);
}
