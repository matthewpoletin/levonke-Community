package ru.mp.levonke.service;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.web.model.UserRequest;

public interface UserService {
	User save(User user);
	User getUser(Integer userId);
	// TODO: updateUser method
	void delete(Integer userId);
}
