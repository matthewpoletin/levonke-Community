package ru.mp.levonke.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.service.UserServiceImpl;
//import ru.mp.levonke.web.model.UserResponse;

@RestController
@RequestMapping(UserController.USER_BASE_URI)
public class UserController {

	public static final String USER_BASE_URI = "/users";

	@Autowired
	UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<User> getUsers() {
		return userService.getUsers();
//		ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
//		userService.getUsers().forEach(user -> userResponses.add(new UserResponse(user)));
//		return userResponses;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userId") final Integer userId) {
		return userService.read(userId);
	}

	// TODO: add response with user id on body
	// TODO: add check for existing username
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@RequestBody User user, HttpServletResponse response) {
//		userService.create(user);
		User savedUser = userService.create(user);
//		response.
//		response.addHeader(HttpHeaders.LOCATION, "/users/" + savedUser.getId());
	}

	// TODO: implement this method
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
	public void updateUser(@PathVariable("userId") final Integer userId) {
		userService.update(userId);
//		response.addHeader(HttpHeaders.LOCATION, "/users/" + user.getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") final Integer userId) {
		userService.delete(userId);
//		User user = userService.save(user);
//		userService.save(user);
//		response.addHeader(HttpHeaders.LOCATION, "/users/" + user.getId());
	}

}
