package test.ua.nure.gunko.rent.web.command.client;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.client.CreateOrderCommand;

public class CreateOrderCommandTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		when(session.getAttribute("order")).thenReturn(new OrderDao().findOrdersById(16));
		when(request.getParameter("payment")).thenReturn("3000");
		new CreateOrderCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoPost5Fine() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		when(session.getAttribute("order")).thenReturn(new OrderDao().findOrdersById(16));
		when(request.getParameter("payment")).thenReturn("1500");
		new CreateOrderCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoPost2() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(session.getAttribute("userRole")).thenReturn(null);
		assertEquals(Path.LOGIN__URL, new CreateOrderCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoPost3() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		assertEquals(Path.COMMAND__ERROR_PAGE + "error.invalid.permission",
				new CreateOrderCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoPost4() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		when(session.getAttribute("order")).thenReturn(null);
		when(request.getParameter("payment")).thenReturn("ads");
		assertEquals(Path.COMMAND__ERROR_PAGE + "error.invalid.request",
				new CreateOrderCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoGet() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		assertEquals(Path.PAGE__ERROR_PAGE, new CreateOrderCommand().execute(request, response, ActionType.GET));
	}

}
