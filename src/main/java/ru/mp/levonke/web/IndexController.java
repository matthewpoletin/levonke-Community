package ru.mp.levonke.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = GET)
	@ResponseBody
	public String helloWorld() {
		return "Hello World";
	}

	@RequestMapping(value = "/reverse/{text}", method = GET)
	@ResponseBody
	public String reverseRequest(@PathVariable("text") String text) {
		return reverse(text);
	}

	String reverse(String text) {
		return new StringBuilder(text).reverse().toString();
	}

}
