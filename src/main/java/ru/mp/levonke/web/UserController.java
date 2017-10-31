package ru.mp.levonke.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.service.UserServiceImpl;
import ru.mp.levonke.web.model.UserRequest;
import ru.mp.levonke.web.model.UserResponse;

import java.util.ArrayList;

@RestController
@RequestMapping(UserController.USER_BASE_URI)
public class UserController {

	public static final String USER_BASE_URI = "/api/community/users";

	@Autowired
	UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<UserResponse> getUsers() {
		ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
		userService.getUsers().forEach(user -> userResponses.add(new UserResponse(user)));
		return userResponses;
	}

	// TODO: add check for existing username (or leave UNIQUE)
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@RequestBody UserRequest userRequest, HttpServletResponse response) {
		User user = userService.create(userRequest);
		response.addHeader(HttpHeaders.LOCATION, this.USER_BASE_URI + "/" + user.getId());
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public UserResponse getUser(@PathVariable("userId") final Integer userId) {
		return new UserResponse(userService.read(userId));
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
	public UserResponse updateUser(@PathVariable("userId") final Integer userId, @RequestBody UserRequest userRequest) {
		return new UserResponse(userService.update(userId, userRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") final Integer userId) {
		userService.delete(userId);
	}

}
