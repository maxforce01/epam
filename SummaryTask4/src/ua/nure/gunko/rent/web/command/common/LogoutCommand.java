package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class LogoutCommand extends Command {
	private static final Logger log = Logger.getLogger(LogoutCommand.class);
	/**
	 * logout from system.
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = -353150112207085063L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command starts");

		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		log.debug("Command finished");
		return Path.LOGIN__URL;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command starts");

		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		log.debug("Command finished");
		return Path.LOGIN__PAGE;
	}

}
