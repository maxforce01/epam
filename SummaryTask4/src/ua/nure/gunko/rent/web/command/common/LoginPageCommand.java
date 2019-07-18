package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class LoginPageCommand extends Command{
	private static final Logger log = Logger.getLogger(LoginPageCommand.class);
	/**
	 * Show login page.
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command start");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if(user != null && role != null) {
			return Path.ACCOUNT_PAGE;
		}
		log.debug("Command finished");
		return Path.LOGIN__PAGE;
	}
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command start");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if(user != null && role != null) {
			return Path.ACCOUNT_URL;
		}
		log.debug("Command finished");
		return Path.LOGIN__URL;
	}
}
