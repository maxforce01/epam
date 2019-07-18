package ua.nure.gunko.rent.web.command.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class AcceptedViewCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String message = (String) request.getParameter("success");
		return Path.COMMAND__ACCEPTED_PAGE_URL+message;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String message = (String) request.getParameter("success");
		request.setAttribute("success", message);
		return Path.COMMAND__ACCEPTED_PAGE;
	}


	
}
