package ru.mp.levonke.service;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.web.model.UserRequest;

public interface UserService {
	Iterable<User> getUsers();
	User create(UserRequest	userRequest);
	User read(Integer userId);
	User update(Integer userId, UserRequest userRequest);
	void delete(Integer userId);
}
