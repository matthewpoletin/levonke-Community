package ru.mp.levonke.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class IndexControllerTest {

	private static IndexController indexController = new IndexController();

	@Test
	void reverseTest() {
		assertEquals(indexController.reverse("abc"), "cba");
	}

}
