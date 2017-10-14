package ru.mp.levonke.web;

import java.util.HashMap;
//import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.mp.levonke.model.User;
import ru.mp.levonke.service.UserService;


@RestController
@RequestMapping(UserController.USER_BASE_URI)
public class UserController {

	public static final String USER_BASE_URI = "v1/users/";

	@Autowired
	UserService userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userId") final Integer userId) {
		return userService.getUser(userId);
	}

//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public HashMap<Integer, User> getAll()
//	{
////		return userService.getUsers();
//		return null;
//	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public HashMap<Integer, User> getPage(@RequestParam("page") int page, @RequestParam("size") int size)
	{
		return userService.getUsers();
	}


//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public HashMap<Integer, User> searchUsers()
//	{
//		return userService.getUsers();
//	}

}
