package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class ErrorCommand extends Command{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ErrorCommand.class);
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Start executing Command");
		String message = (String) request.getParameter("message");
		log.debug("End executing command");
		return Path.COMMAND__ERROR_PAGE+message;
	}
	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Start executing Command");
		String message = (String) request.getParameter("message");
		request.setAttribute("errorMessage", message);
		log.debug("End executing command");
		return Path.PAGE__ERROR_PAGE;
	}
	

}
