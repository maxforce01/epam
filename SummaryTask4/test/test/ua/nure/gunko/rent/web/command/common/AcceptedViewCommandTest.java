package test.ua.nure.gunko.rent.web.command.common;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.common.AcceptedViewCommand;

public class AcceptedViewCommandTest extends Mockito {

	@Test
	public void testDoPost()throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getParameter("success")).thenReturn("success");
		new AcceptedViewCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoGet() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getParameter("success")).thenReturn("success");
		assertEquals(new AcceptedViewCommand().execute(request, response, ActionType.GET), Path.COMMAND__ACCEPTED_PAGE);
	}

}
