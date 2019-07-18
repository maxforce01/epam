package test.ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.command.common.UpdateSettingsCommand;

public class UpdateSettingsCommandTest extends Mockito{

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		User user = new UserDao().findUser((long) 2);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		when(request.getParameter("login")).thenReturn(user.getLogin());
		when(request.getParameter("email")).thenReturn(user.getEmail());
		when(request.getParameter("locale")).thenReturn(user.getLocale());
		new UpdateSettingsCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoGet() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		new UpdateSettingsCommand().execute(request, response, ActionType.GET);
	}

}
