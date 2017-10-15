package ru.mp.levonke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.repository.UserRepository;
import ru.mp.levonke.web.model.UserRequest;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(Integer userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new EntityNotFoundException("Customer '{" + userId + "}' not found");
		}
		return user;
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
//		User user = new User();
//			user.setUsername(userRequest.getUsername());
////				.setUsername(userRequest.getUsername());
////
//////		Order order = new Order()
//////				.setSum(orderRequest.getSum())
//////				.setDiscount(orderRequest.getDiscount());
////
//		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Integer userId) {
		userRepository.delete(userId);
	}

//	public HashMap<Integer, User> getUsers() {
////		return users;
//	}
}
