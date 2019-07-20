/**
 * 
 */
package test.ua.nure.gunko.rent.web.command.common;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.common.ViewSettingsCommand;

/**
 * @author maxforce01
 *
 */
public class ViewSettingsCommandTest extends Mockito{

	/**
	 * Test method for {@link ua.nure.gunko.rent.web.command.common.ViewSettingsCommand#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new UserDao().findUser((long) 2));
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		assertEquals(Path.ACCOUNT_SETTINGS_URL, new ViewSettingsCommand().execute(request, response, ActionType.POST));
	}


	/**
	 * Test method for {@link ua.nure.gunko.rent.web.command.common.ViewSettingsCommand#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */

	@Test
	public void testDoGet() throws Exception {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(new UserDao().findUser((long) 2));
		when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
		assertEquals(Path.ACCOUNT_SETTINGS_PAGE, new ViewSettingsCommand().execute(request, response, ActionType.GET));
	}

}
