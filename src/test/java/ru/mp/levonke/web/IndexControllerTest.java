package ru.mp.levonke.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class IndexControllerTest {

	private static IndexController indexController = new IndexController();

	@Test
	void reverseTest() {
		assertEquals(indexController.reverse("abc"), "cba");
		assertEquals(indexController.reverse("Hello, world!"), "!dlrow ,olleH");
		assertEquals(indexController.reverse("你好，世界！"), "！界世，好你");
	}

}
