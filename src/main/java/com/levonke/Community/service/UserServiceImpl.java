package com.levonke.Community.service;

import com.levonke.Community.domain.Team;
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
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<User> getUsersWithUsername(String username, Integer page, Integer size) {
		return userRepository.getUsersByUsernameContainingIgnoreCase(username, PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public User createUser(UserRequest userRequest) {
		User user = new User()
			.setUsername(userRequest.getUsername())
			.setAvatar(userRequest.getAvatar())
			.setBio(userRequest.getAvatar())
			.setForename(userRequest.getForename())
			.setSurname(userRequest.getSurname())
			.setRegEmail(userRequest.getRegEmail())
			.setPubEmail(userRequest.getPubEmail())
			.setFbLink(userRequest.getFbLink())
			.setGhLink(userRequest.getGhLink())
			.setPassword(userRequest.getPassword());
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
	@Transactional(readOnly = true)
	public User getUserByRegEmail(String regEmail) {
		return userRepository.getUserByRegEmailIgnoreCase(regEmail);
	}
	
	@Override
	@Transactional
	public User updateUserById(Integer userId, UserRequest userRequest) {
		User user = this.getUserById(userId);
		user.setUsername(userRequest.getUsername() != null ? userRequest.getUsername() : user.getUsername());
		user.setAvatar(userRequest.getAvatar() != null ? userRequest.getAvatar() : user.getAvatar());
		user.setBio(userRequest.getBio() != null ? userRequest.getBio() : user.getBio());
		user.setForename(userRequest.getForename() != null ? userRequest.getForename() : user.getForename());
		user.setSurname(userRequest.getSurname() != null ? userRequest.getSurname() : user.getSurname());
		user.setRegEmail(userRequest.getRegEmail() != null ? userRequest.getRegEmail() : user.getRegEmail());
		user.setPubEmail(userRequest.getPubEmail() != null ? userRequest.getPubEmail() : user.getPubEmail());
		user.setFbLink(userRequest.getFbLink() != null ? userRequest.getFbLink() : user.getFbLink());
		user.setGhLink(userRequest.getGhLink() != null ? userRequest.getGhLink() : user.getGhLink());
		user.setPassword(userRequest.getPassword() != null ? userRequest.getPassword() : user.getPassword());
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Team> getTeamsWithUser(Integer userId) {
		User user = this.getUserById(userId);
		return new ArrayList<>(user.getTeams());
	}
	
}
