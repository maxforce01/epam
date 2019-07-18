package test.ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.command.common.LoginCommand;

public class LoginCommandTest extends Mockito {

	@Test
	public void testDoPost() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getParameter("login")).thenReturn("Admin");
		when(request.getParameter("password")).thenReturn("password");
		when(request.getSession()).thenReturn(session);
		new LoginCommand().execute(request, response, ActionType.POST);
		
	}

	@Test
	public void testDoGet() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		new LoginCommand().execute(request, response, ActionType.GET);
	}

}
