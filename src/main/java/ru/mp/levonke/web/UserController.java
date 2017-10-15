package ru.mp.levonke.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.mp.levonke.domain.User;
import ru.mp.levonke.service.UserServiceImpl;
import ru.mp.levonke.web.model.UserRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(UserController.USER_BASE_URI)
public class UserController {

	public static final String USER_BASE_URI = "/users";

	@Autowired
	UserServiceImpl userService;

	// TODO: create getAllUsers query

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userId") final Integer userId) {
		return userService.getUser(userId);
	}

	// TODO: add response with user id on body
	// TODO: add check for existing username
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createUser(@RequestBody User user, HttpServletResponse response) {
//		User user = userService.save(user);
		userService.save(user);
//		User savedUser = userService.save(user);
//		response.addHeader(HttpHeaders.LOCATION, "/users/" + savedUser.getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") final Integer userId) {
		userService.delete(userId);
//		User user = userService.save(user);
//		userService.save(user);
//		response.addHeader(HttpHeaders.LOCATION, "/users/" + user.getId());
	}



//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public HashMap<Integer, User> getAll()
//	{
////		return userService.getUsers();
//		return null;
//	}

//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public HashMap<Integer, User> getPage(@RequestParam("page") int page, @RequestParam("size") int size) {
////		return userService.getUsers();
//		return null;
//	}

}
