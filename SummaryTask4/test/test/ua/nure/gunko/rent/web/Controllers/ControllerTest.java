package test.ua.nure.gunko.rent.web.Controllers;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Controllers.Controller;

public class ControllerTest extends Mockito{

	@Test
	public void testController() {
		new Controller();
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("command")).thenReturn(null);
		when(session.getAttribute("user")).thenReturn(new User());
		new Controller().doPost(request, response);
	}

}
