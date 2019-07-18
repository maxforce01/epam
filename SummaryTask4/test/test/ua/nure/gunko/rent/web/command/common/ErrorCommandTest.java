package test.ua.nure.gunko.rent.web.command.common;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.common.ErrorCommand;

public class ErrorCommandTest  extends Mockito{

	@Test
	public void testDoPost()throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getParameter("message")).thenReturn("message");
		new ErrorCommand().execute(request, response, ActionType.POST);
	}

	@Test
	public void testDoGet() throws Exception{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getParameter("message")).thenReturn("message");
		assertEquals(new ErrorCommand().execute(request, response, ActionType.GET), Path.PAGE__ERROR_PAGE);
	}

}
