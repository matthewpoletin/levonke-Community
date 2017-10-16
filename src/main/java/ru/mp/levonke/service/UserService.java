package ru.mp.levonke.service;

import java.util.ArrayList;

import ru.mp.levonke.domain.User;

public interface UserService {
	Iterable<User> getUsers();
	User create(User user);
	User read(Integer userId);
	User update(Integer userId);
	void delete(Integer userId);
}
