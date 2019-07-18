package test.ua.nure.gunko.rent.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ua.nure.gunko.rent.web.Validator;

class ValidatorTest {

	@Test
	void test() {
		new Validator();
	}
	
	@Test
	void testregisterFormValidate() {
		assertTrue(Validator.registerFormValidate("login", "password", "password", "user@gmai.com"));
		assertFalse(Validator.registerFormValidate(null, "password", "password", "user@gmai.com"));
		assertFalse(Validator.registerFormValidate("login", null, "password", "user@gmai.com"));
		assertFalse(Validator.registerFormValidate("login", "password", "p", "user@gmai.com"));
		assertFalse(Validator.registerFormValidate("login", "password", "password", "usser"));
	}

	@Test
	void testsettingsFormValidate() {
		assertFalse(Validator.settingsFormValidate("user", "user123", "ru"));
		assertFalse(Validator.settingsFormValidate("u", "user123", "ru"));
		assertFalse(Validator.settingsFormValidate("user", "user123", "ruuu"));
		assertTrue(Validator.settingsFormValidate("user", "user@user.com", "ru"));
		assertFalse(Validator.settingsFormValidate(null, null, null));
		assertFalse(Validator.settingsFormValidate(null, null, "ruu"));
		assertFalse(Validator.settingsFormValidate(null, null, "r"));
	}
	@Test
	void testisNumeric() {
		assertTrue(Validator.isNumeric("23"));
		assertFalse(Validator.isNumeric(null));
		assertFalse(Validator.isNumeric("aad"));
	}
}
