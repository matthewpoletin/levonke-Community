package com.levonke.Community.service;

import com.levonke.Community.domain.User;
import com.levonke.Community.repository.UserRepository;
import com.levonke.Community.web.model.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
	public Page<User> getUsers(Integer page, Integer size) {
		if(page == null)
			page = 0;
		if (size == null) {
			size = 25;
		}
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public User createUser(UserRequest userRequest) {
		User user = new User()
			.setUsername(userRequest.getUsername())
			.setPassword(userRequest.getPassword())
			.setForename(userRequest.getForename())
			.setSurname(userRequest.getSurname())
			.setRegEmail(userRequest.getRegEmail())
			.setPubEmail(userRequest.getPubEmail())
			.setFbLink(userRequest.getFbLink())
			.setGhLink(userRequest.getGhLink());
		return userRepository.save(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUserById(Integer userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException("User '{" + userId + "}' not found"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsernameIgnoreCase(username);
	}
	
	@Override
	@Transactional
	public User updateUserById(Integer userId, UserRequest userRequest) {
		User user = this.getUserById(userId);
		user.setUsername(userRequest.getUsername() != null ? userRequest.getUsername() : user.getUsername());
		user.setPassword(userRequest.getPassword() != null ? userRequest.getPassword() : user.getPassword());
		user.setForename(userRequest.getForename() != null ? userRequest.getForename() : user.getForename());
		user.setSurname(userRequest.getSurname() != null ? userRequest.getSurname() : user.getSurname());
		user.setRegEmail(userRequest.getRegEmail() != null ? userRequest.getRegEmail() : user.getRegEmail());
		user.setPubEmail(userRequest.getPubEmail() != null ? userRequest.getPubEmail() : user.getPubEmail());
		user.setFbLink(userRequest.getFbLink() != null ? userRequest.getFbLink() : user.getFbLink());
		user.setGhLink(userRequest.getGhLink() != null ? userRequest.getGhLink() : user.getGhLink());
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Integer> getTeamsOfUser(Integer userId) {
		List<Integer> teamsId = new ArrayList<>();
		this.getUserById(userId).getTeams().forEach(team -> teamsId.add(team.getId()));
		return teamsId;
	}
	
}
