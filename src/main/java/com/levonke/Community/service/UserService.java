package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.web.model.UserRequest;

public interface UserService {
	Iterable<User> getUsers();
	User create(UserRequest	userRequest);
	User read(Integer userId);
	User update(Integer userId, UserRequest userRequest);
	void delete(Integer userId);
}
