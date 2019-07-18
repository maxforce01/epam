package test.ua.nure.gunko.rent.web;


import org.junit.Test;

import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Sender;

public class SenderTest {

	@Test
	public void testSendMessage() {
		new Sender();
		User user = new User();
		user.setEmail("user@mail.com");
		Sender.sendMessage(user, "asd");
	}

}

