package ru.mp.levonke.service;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.web.model.UserRequest;

public interface UserService {
	User getUser(Integer userId);

	User save(User user);
}
