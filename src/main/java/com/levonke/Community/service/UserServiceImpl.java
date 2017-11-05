package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.web.model.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> getUsers(Integer page, Integer size) {
		if(page == null)
			page = 1;
		if (size == null) {
			size = 25;
		}
		return userRepository.findAll(new PageRequest(page - 1, size)).getContent();
	}
	
	@Override
	@Transactional
	public User create(UserRequest userRequest) {
		User user = new User()
			.setUsername(userRequest.getUsername())
			.setFirstname(userRequest.getFirstname())
			.setSurname(userRequest.getSurname())
			.setRegEmail(userRequest.getRegEmail())
			.setPubEmail(userRequest.getPubEmail())
			.setFbLink(userRequest.getFbLink())
			.setGhLink(userRequest.getGhLink());
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
	public User update(Integer userId, UserRequest userRequest) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new EntityNotFoundException("User '{" + userId + "}' not found");
		}
		user.setUsername(userRequest.getUsername() != null ? userRequest.getUsername() : user.getUsername());
		user.setFirstname(userRequest.getFirstname() != null ? userRequest.getFirstname() : user.getFirstname());
		user.setSurname(userRequest.getSurname() != null ? userRequest.getSurname() : user.getSurname());
		user.setRegEmail(userRequest.getRegEmail() != null ? userRequest.getRegEmail() : user.getRegEmail());
		user.setPubEmail(userRequest.getPubEmail() != null ? userRequest.getPubEmail() : user.getPubEmail());
		user.setFbLink(userRequest.getFbLink() != null ? userRequest.getFbLink() : user.getFbLink());
		user.setGhLink(userRequest.getGhLink() != null ? userRequest.getGhLink() : user.getGhLink());
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Integer userId) {
		userRepository.delete(userId);
	}

}
