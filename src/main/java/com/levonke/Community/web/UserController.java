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
@RequestMapping(UserController.USER_BASE_URI)
public class UserController {

	public static final String USER_BASE_URI = "/api/community/users";

	private final UserServiceImpl userService;
	
	@Autowired
	UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserResponse> getUsers(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		return userService.getUsers(page, size)
			.stream()
			.map(UserResponse::new)
			.collect(Collectors.toList());
	}

	// TODO: add check for existing username (or leave UNIQUE)
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@RequestBody UserRequest userRequest, HttpServletResponse response) {
		User user = userService.createUser(userRequest);
		response.addHeader(HttpHeaders.LOCATION, this.USER_BASE_URI + "/" + user.getId());
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public UserResponse getUser(@PathVariable("userId") final Integer userId) {
		return new UserResponse(userService.getUserById(userId));
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
	public UserResponse updateUser(@PathVariable("userId") final Integer userId, @RequestBody UserRequest userRequest) {
		return new UserResponse(userService.updateUser(userId, userRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") final Integer userId) {
		userService.deleteUser(userId);
	}

}
