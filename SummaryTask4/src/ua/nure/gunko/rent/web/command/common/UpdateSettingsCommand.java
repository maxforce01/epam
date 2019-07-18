package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class UpdateSettingsCommand extends Command{

	/**
	 * Update user's settings.
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if(user == null || role == null) {
			return Path.LOGIN__URL;
		}
		
		String login = (String) request.getParameter("login");
		String email = (String) request.getParameter("email");
		String locale = (String) request.getParameter("locale");
		if(!Validator.settingsFormValidate(login, email, locale)) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		user.setLogin(login);
		user.setLocale(locale);
		user.setEmail(email);
		
		if(UserDao.updateUser(user)) {
			return Path.ACCOUNT_URL;
		}
		String forward = "error.invalid.data";
		request.setAttribute("errorMessage", forward);
		return Path.COMMAND__ERROR_PAGE+forward;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		request.setAttribute("errorMessage", errorMessage);
		return Path.PAGE__ERROR_PAGE;
	}

	
}
