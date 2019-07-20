package test.ua.nure.gunko.rent.web.command.admin;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.admin.BanUserCommand;

public class BanUserCommandTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		assertEquals(Path.LOGIN__URL, new BanUserCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoPost2() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		assertEquals(Path.COMMAND__ERROR_PAGE+"error.invalid.permission", new BanUserCommand().execute(request, response, ActionType.POST));
	}
	
	@Test
	public void testDoPost3() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		when(request.getParameter("user_id")).thenReturn(null);
		assertEquals(Path.COMMAND__ERROR_PAGE+"error.invalid.data", new BanUserCommand().execute(request, response, ActionType.POST));
	}
	
	@Test
	public void testDoPost4() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		when(request.getParameter("user_id")).thenReturn("3");
		assertEquals(Path.COMMAND__ACCEPTED_PAGE_URL+"success.ban.user", new BanUserCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoGet() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		assertEquals(Path.PAGE__ERROR_PAGE, new BanUserCommand().execute(request, response, ActionType.GET));
	}

}
