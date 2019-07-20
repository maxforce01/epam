package test.ua.nure.gunko.rent.web.command.manager;

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
import ua.nure.gunko.rent.web.command.manager.OrderAcceptCommand;

public class OrderAcceptCommandTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(session.getAttribute("userRole")).thenReturn(Role.MANAGER);
		assertEquals(Path.LOGIN__URL, new OrderAcceptCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoPost2() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.CLIENT);
		assertEquals(Path.COMMAND__ERROR_PAGE + "error.invalid.permission",
				new OrderAcceptCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoPost3() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.MANAGER);
		when(request.getParameter("status")).thenReturn(null);
		when(request.getParameter("order_id")).thenReturn(null);
		new OrderAcceptCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoPost4() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.MANAGER);
		when(request.getParameter("status")).thenReturn("accepted");
		when(request.getParameter("order_id")).thenReturn("1");
		new OrderAcceptCommand().execute(request, response, ActionType.POST);
	}
	
	@Test
	public void testDoPost5() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.MANAGER);
		when(request.getParameter("status")).thenReturn("DENIED");
		when(request.getParameter("order_id")).thenReturn("1");
		when(request.getParameter("cause")).thenReturn("test");
		new OrderAcceptCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoGet() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		assertEquals(Path.PAGE__ERROR_PAGE, new OrderAcceptCommand().execute(request, response, ActionType.GET));
	}

}
