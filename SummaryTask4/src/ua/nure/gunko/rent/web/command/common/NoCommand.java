package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class NoCommand extends Command {

	/**
	 * No command
	 * return account page.
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(NoCommand.class);
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command starts");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if(user == null || role == null) {
			return Path.LOGIN__URL;
		}
		log.debug("Command finished");
		return Path.ACCOUNT_URL;
	}
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command starts");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if(user == null || role == null) {
			return Path.LOGIN__PAGE;
		}
		log.debug("Command finished");
		return Path.ACCOUNT_PAGE;
	}
	
	
}
