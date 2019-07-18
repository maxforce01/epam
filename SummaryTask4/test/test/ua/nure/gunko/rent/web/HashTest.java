package test.ua.nure.gunko.rent.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ua.nure.gunko.rent.web.Hash;

class HashTest {
	private String expected = "5f4dcc3b5aa765d61d8327deb882cf99";
	@Test
	void test() {
		new Hash();
		 assertEquals(expected, Hash.hash("password"));
	}

}
