package test.ua.nure.gunko.rent.web.command;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.gunko.rent.web.command.CommandContainer;
import ua.nure.gunko.rent.web.command.common.LoginCommand;
import ua.nure.gunko.rent.web.command.common.NoCommand;

public class CommandContainerTest {

	@Test
	public void testGet() {
		assertSame(LoginCommand.class, CommandContainer.get("login").getClass());
	}

	@Test
	public void testGet2() {
		assertSame(NoCommand.class, CommandContainer.get("not").getClass());
	}

	@Test
	public void testGet3() {
		assertSame(NoCommand.class, CommandContainer.get(null).getClass());
	}

	@Test
	public void test() {
		new CommandContainer();
	}

}
