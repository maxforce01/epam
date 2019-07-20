package test.ua.nure.gunko.rent.web.command.admin;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.admin.CreateCarCommand;

public class CreateCarCommandTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		assertEquals(Path.LOGIN__URL, new CreateCarCommand().execute(request, response, ActionType.POST));
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
				new CreateCarCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoPost3() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		when(request.getParameter("brand")).thenReturn("brand");
		when(request.getParameter("model")).thenReturn("model");
		when(request.getParameter("class")).thenReturn("3");
		when(request.getParameter("vin")).thenReturn("vin");
		when(request.getParameter("text")).thenReturn("text");
		when(request.getParameter("price")).thenReturn("300");
		assertEquals(Path.COMMAND__ACCEPTED_PAGE_URL + "success.create.car",
				new CreateCarCommand().execute(request, response, ActionType.POST));
		Car car = new CarDao().findCarByVin("vin");
		CarDao.deleteCar(car);
	}

	@Test
	public void testDoPost4() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new User());
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		when(request.getParameter("brand")).thenReturn(null);
		when(request.getParameter("model")).thenReturn(null);
		when(request.getParameter("class")).thenReturn(null);
		when(request.getParameter("vin")).thenReturn(null);
		when(request.getParameter("text")).thenReturn(null);
		when(request.getParameter("price")).thenReturn(null);
		assertEquals(Path.COMMAND__ERROR_PAGE + "error.invalid.data",
				new CreateCarCommand().execute(request, response, ActionType.POST));
	}

	@Test
	public void testDoGet() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		assertEquals(Path.PAGE__ERROR_PAGE, new CreateCarCommand().execute(request, response, ActionType.GET));
	}

}
