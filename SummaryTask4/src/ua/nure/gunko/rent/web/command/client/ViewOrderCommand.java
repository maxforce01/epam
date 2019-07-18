package ua.nure.gunko.rent.web.command.client;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class ViewOrderCommand extends Command {
	private static final Logger log = Logger.getLogger(ViewOrderCommand.class);

	/**
	 * Show form for create new order
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		return Path.COMMAND__ERROR_PAGE + errorMessage;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command start");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if (user == null || role == null) {
			return Path.LOGIN__PAGE;
		}

		if (role != Role.CLIENT) {
			String errorMessage = "error.invalid.permission";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}

		Integer id = Integer.parseInt(request.getParameter("car_id"));
		Car car = new CarDao().findCarById(id);
		if (car == null) {
			String errorMessage = "error.bad.car";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		request.setAttribute("car", car);

		log.debug("Command finished");
		return Path.COMMAND__VIEW_ORDER;
	}

}
