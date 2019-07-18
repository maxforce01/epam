package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.db.Fields;
import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class RegisterCommand extends Command {

	/**
	 * Register new user in system.
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = -5106736479068858102L;

	private static final Logger log = Logger.getLogger(LoginCommand.class);


	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command starts");
		String login = request.getParameter("login");
		log.trace("Request parameter: loging --> " + login);
		String password = request.getParameter("password");
		String password_confirms = request.getParameter("password-confirm");
		String email = request.getParameter("email");
		String errorMessage = null;
		String forward = Path.COMMAND__ERROR_PAGE;
		if (!Validator.registerFormValidate(login, password, password_confirms, email)) {
			errorMessage = "error.invalid.data";
			log.error("errorMessage --> " + errorMessage);
			return forward+errorMessage;
		}
		User user = new User();
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		user.setRoleId(Fields.USER_ROLE);
		user.setLocale(Fields.DEFAULT_LOCALE);
		user.setStatus(false);
		if (UserDao.createUser(user)) {
			forward = Path.LOGIN__URL;
		}

		return forward;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		request.setAttribute("errorMessage", errorMessage);
		return Path.PAGE__ERROR_PAGE;
	}

	

}
