package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.UserRequest;

import java.util.List;

public interface UserService {
	List<User> getUsers(Integer page, Integer count);
	User create(UserRequest	userRequest);
	User read(Integer userId);
	User update(Integer userId, UserRequest userRequest);
	void delete(Integer userId);
}
