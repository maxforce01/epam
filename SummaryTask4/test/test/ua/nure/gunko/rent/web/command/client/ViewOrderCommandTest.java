package test.ua.nure.gunko.rent.web.command.client;

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
import ua.nure.gunko.rent.web.command.client.ViewOrderCommand;

public class ViewOrderCommandTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		assertEquals(Path.COMMAND__ERROR_PAGE + "error.invalid.request",
				new ViewOrderCommand().execute(request, response, ActionType.POST));

	}

	@Test
	public void testDoGet() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		when(request.getParameter("car_id")).thenReturn("1");
		assertEquals(Path.COMMAND__VIEW_ORDER, new ViewOrderCommand().execute(request, response, ActionType.GET));
	}

	@Test
	public void testDoGet2() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		when(request.getParameter("car_id")).thenReturn("10000");
		assertEquals(Path.PAGE__ERROR_PAGE, new ViewOrderCommand().execute(request, response, ActionType.GET));
	}

	@Test
	public void testDoGet3() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(session.getAttribute("userRole")).thenReturn(null);
		when(request.getParameter("car_id")).thenReturn("asd");
		assertEquals(Path.LOGIN__PAGE, new ViewOrderCommand().execute(request, response, ActionType.GET));
	}
	@Test
	public void testDoGet4() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		when(request.getParameter("car_id")).thenReturn("asd");
		assertEquals(Path.PAGE__ERROR_PAGE, new ViewOrderCommand().execute(request, response, ActionType.GET));
	}
}
