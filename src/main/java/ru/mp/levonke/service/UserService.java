package ru.mp.levonke.service;

import java.util.HashMap;
import java.util.Hashtable;
import org.springframework.stereotype.Service;

import ru.mp.levonke.model.User;

@Service
public class UserService {

	HashMap <Integer, User> users = new HashMap<Integer, User>();

	public UserService() {
		User user1 = new User();
		user1.setId(1);
		user1.setLogin("MatthewPoletin");
		user1.setName("Matthew");
		user1.setSurname("Poletin");
		user1.setPubEmail("contact@matthewpoletin.ru");
		user1.setRegEmail("poletinm@yandex.ru");
		user1.setGhLink("MatthewPoletin");
		users.put(user1.getId(), user1);

		User user2 = new User();
		user2.setId(2);
		user2.setLogin("AlexanderPoletin");
		user2.setName("Alexander");
		user2.setSurname("Poletin");
		user2.setPubEmail("polall@prime.ru");
		user2.setRegEmail("polall@yandex.ru");
		users.put(user2.getId(), user2);
	}

	public User getUser(Integer userId) {
		if (users.containsKey(userId))
			return users.get(userId);
		else
			return null;
	}

	public HashMap<Integer, User> getUsers() {
		return users;
	}
}
