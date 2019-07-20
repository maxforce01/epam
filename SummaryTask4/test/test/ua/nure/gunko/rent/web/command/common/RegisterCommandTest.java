package test.ua.nure.gunko.rent.web.command.common;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.common.RegisterCommand;

public class RegisterCommandTest extends Mockito{

	@Test
	public void testDoPost() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getParameter("login")).thenReturn("test");
		when(request.getParameter("password")).thenReturn("password");
		when(request.getParameter("password-confirm")).thenReturn("password");
		when(request.getParameter("email")).thenReturn("test@test.com");
		assertEquals(Path.LOGIN__URL, new RegisterCommand().execute(request, response, ActionType.POST));
		User user =  new UserDao().findUserByLogin("test");
		UserDao.deleteUser(user);
	}

	@Test
	public void testDoGet() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		assertEquals(Path.PAGE__ERROR_PAGE, new RegisterCommand().execute(request, response, ActionType.GET));
	}

}
