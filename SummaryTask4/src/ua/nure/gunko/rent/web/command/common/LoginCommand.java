package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.db.FineDao;
import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Fine;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Hash;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class LoginCommand extends Command {

	/**
	 * Command for enter to the system.
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(LoginCommand.class);

	@Override
	 protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("errorMessage", "error.invalid.request");
		return Path.PAGE__ERROR_PAGE;
	}
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {

		log.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from the request
		String login = request.getParameter("login");
		log.trace("Request parameter: loging --> " + login);

		String password = request.getParameter("password");

		// error handler
		String errorMessage = null;
		String forward = Path.COMMAND__ERROR_PAGE;

		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			errorMessage = "error.bad.login.password";
			forward+=errorMessage;
			log.error("errorMessage --> " + errorMessage);
			return forward;
		}

		User user;
		user = new UserDao().findUserByLogin(login);
		log.trace("Found in DB: user --> " + user);
		if (user == null || !Hash.hash(password).equals(user.getPassword())) {
			errorMessage = "error.cannot.find.user";
			forward+=errorMessage;
			log.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			if (user.isStatus()) {
				errorMessage = "error.user.isban";
				forward+=errorMessage;
				log.error("errorMessage --> " + errorMessage);
				return forward;
			}
			Role userRole = Role.getRole(user);
			log.trace("userRole --> " + userRole);

			forward = Path.ACCOUNT_URL;

			session.setAttribute("user", user);
			log.trace("Set the session attribute: user --> " + user);

			session.setAttribute("userRole", userRole);
			Fine fine = new FineDao().findFineById(user.getId());
			session.setAttribute("fine", fine);
			log.trace("Set the session attribute: userRole --> " + userRole);
			log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
		}

		log.debug("Command finished");
		return forward;
	}
}
