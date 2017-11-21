package com.levonke.Community.web;

import com.levonke.Community.domain.User;
import com.levonke.Community.service.UserServiceImpl;
import com.levonke.Community.web.model.UserRequest;
import com.levonke.Community.web.model.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.userBaseURI)
public class UserController {

	static final String userBaseURI = "/api/community";

	private final UserServiceImpl userService;
	
	@Autowired
	UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserResponse> getUsers(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		return userService.getUsers(page, size)
			.stream()
			.map(UserResponse::new)
			.collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public UserResponse createUser(@RequestBody UserRequest userRequest, HttpServletResponse response) {
		User user = userService.createUser(userRequest);
		response.addHeader(HttpHeaders.LOCATION, userBaseURI + "/" + user.getId());
		return new UserResponse(user);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public UserResponse getUser(@PathVariable("userId") final Integer userId) {
		return new UserResponse(userService.getUserById(userId));
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PATCH)
	public UserResponse updateUser(@PathVariable("userId") final Integer userId, @RequestBody UserRequest userRequest) {
		return new UserResponse(userService.updateUserById(userId, userRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") final Integer userId) {
		userService.deleteUserById(userId);
	}

}
