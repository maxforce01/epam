package ua.nure.gunko.rent.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.gunko.rent.web.ActionType;


public abstract class Command implements Serializable {	
	private static final long serialVersionUID = 8879403039606311780L;

	/**
	 * Execution method for command.
	 * @return Address to go once the command is executed.
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response,ActionType type)
			throws IOException, ServletException{
		String forward = null;
		if (type == ActionType.GET) {
			forward = doGet(request, response);
		} else {
			forward = doPost(request, response);
		}
		return forward;
	}
	
	
	protected abstract String doPost(HttpServletRequest request, HttpServletResponse response);


	protected abstract String doGet(HttpServletRequest request, HttpServletResponse response);


	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}

