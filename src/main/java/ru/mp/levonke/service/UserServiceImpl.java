package ru.mp.levonke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.repository.UserRepository;
import ru.mp.levonke.web.model.UserRequest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Currency;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> getUsers() {
		return userRepository.findAll();
//
//		ArrayList<User> users = new ArrayList<User>();
//		userRepository.findAll().forEach(users::add);
//		return users;
	}

	@Override
	@Transactional
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User read(Integer userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new EntityNotFoundException("User '{" + userId + "}' not found");
		}
		return user;
	}

	@Override
	@Transactional
	public User update(Integer userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new EntityNotFoundException("User '{" + userId + "}' not found");
		}
//		userRepository.
//		return user.;
		return null;
	}

	@Override
	@Transactional
	public void delete(Integer userId) {
		userRepository.delete(userId);
	}

}
